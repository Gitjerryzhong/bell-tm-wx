package cn.edu.bnuz.bell

import cn.edu.bnuz.bell.wx.User
import com.aliyuncs.CommonRequest
import com.aliyuncs.CommonResponse
import com.aliyuncs.DefaultAcsClient
import com.aliyuncs.IAcsClient
import com.aliyuncs.exceptions.ClientException
import com.aliyuncs.exceptions.ServerException
import com.aliyuncs.http.MethodType
import com.aliyuncs.profile.DefaultProfile
import com.aliyuncs.profile.IClientProfile
import grails.converters.JSON
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus

class SmsValidateController {
    @Value('${bell.aliDomain}')
    String domain
    @Value('${bell.aliAccessId}')
    String accessId
    @Value('${bell.aliAccessSecret}')
    String accessSecret
    @Value('${bell.regionIdForPop}')
    String regionIdForPop
    @Value('${bell.templateCode}')
    String templateCode
    @Value('${bell.signName}')
    String signName

    def index(String phone) {
        def user = User.findByPhone(phone)
        if (user) {
            render([status: 'DUPLICATED'] as JSON)
        } else {
            IClientProfile profile = DefaultProfile.getProfile(regionIdForPop, accessId, accessSecret)
            IAcsClient client = new DefaultAcsClient(profile)

            CommonRequest request = new CommonRequest()
            request.setMethod(MethodType.POST)
            request.setDomain(domain)
            request.setVersion("2017-05-25")
            request.setAction("SendSms")
            request.putQueryParameter("RegionId", regionIdForPop)
            request.putQueryParameter("PhoneNumbers", phone)
            request.putQueryParameter("SignName", signName)
            request.putQueryParameter("TemplateCode", templateCode)
            // 保证4位验证码
            def code = Math.round(Math.random() * 10000)
            while (true) {
                if (code < 1000) {
                    code = Math.round(Math.random() * 10000)
                } else {
                    break
                }
            }
            request.putQueryParameter("TemplateParam", "{\"code\": \"${code}\"}")
            request.putQueryParameter("SmsUpExtendCode", "90999")

            try {
                CommonResponse response = client.getCommonResponse(request)
                def result = JSON.parse(response.getData())
                render([code: code, status: result.Code] as JSON)
            } catch (ServerException e) {
                e.printStackTrace()
                render(status: HttpStatus.BAD_REQUEST)
            } catch (ClientException e) {
                e.printStackTrace()
                render(status: HttpStatus.BAD_REQUEST)
            }
        }
    }
}
