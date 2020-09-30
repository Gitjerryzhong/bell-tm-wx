package cn.edu.bnuz.bell.wx

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import javassist.tools.web.BadHttpRequest
import org.springframework.beans.factory.annotation.Value

@Transactional
class AuthService {
    AuthTokenService authTokenService
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
        authTokenService.accessLog(openid, this.isConcern(openid, accessToken))
        return openid
    }

    Boolean isConcern(String openId, String accessToken) {
        def subscribe = -1
        String path = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=${accessToken}&openid=${openId}&lang=zh_CN"
        URL url = new URL(path)
        HttpURLConnection  conn = (HttpURLConnection) url.openConnection()
        conn.with {
            requestMethod = "GET"
            def json =  JSON.parse(inputStream.text)
            subscribe = json.subscribe
        }
        if (subscribe == -1) {
            throw new BadHttpRequest()
        }
        return subscribe == 1
    }

    def getAccessToken() {
        def accessToken = authTokenService.findToken(appId, appSecret)
        if (!accessToken) {
            String path = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=${appId}&&secret=${appSecret}"
            def tokenFromHttp = getAccessTokenFromHttp(path)
            authTokenService.saveAccessToken(tokenFromHttp, appId, appSecret)
            accessToken = tokenFromHttp.access_token
        }
        return accessToken
    }

    static getAccessTokenFromHttp(String path) {
        URL url = new URL(path)
        HttpURLConnection  conn = (HttpURLConnection) url.openConnection()
        conn.setRequestMethod("GET")   //设置本次请求的方式 ， 默认是GET方式， 参数要求都是大写字母
        conn.setConnectTimeout(5000)  //设置连接超时
        conn.setDoInput(true)  //是否打开输入流 ， 此方法默认为true
        conn.setDoOutput(true)  //是否打开输出流， 此方法默认为false
        conn.connect()  //表示连接
        def code = conn.getResponseCode()
        if (code == 200) {
            InputStream is = conn.getInputStream()
            def strs = is.readLines()
            if (strs && strs.size()) {
                return JSON.parse(strs.get(0))
            }
        }
        return null
    }
}
