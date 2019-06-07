package cn.edu.bnuz.bell.wx

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import org.springframework.beans.factory.annotation.Value

@Transactional
class AuthService {
    @Value('${bell.host}')
    String host
    @Value('${bell.appId}')
    String appId
    @Value('${bell.appSecret}')
    String appSecret

    String getSmsHost() {
        return "${host}/smsValidate"
    }

    String findOpenId(String code) {
        def openid = null
        String path = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId +
                "&secret=" + appSecret +
                "&code=" + code +
                "&grant_type=authorization_code"
        URL url = new URL(path)
        HttpURLConnection  conn = (HttpURLConnection) url.openConnection()
        conn.with {
            requestMethod = "GET"
            def json =  JSON.parse(inputStream.text)
            openid = json.openid
        }
        return openid
    }
}
