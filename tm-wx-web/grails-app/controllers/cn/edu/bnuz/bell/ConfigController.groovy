package cn.edu.bnuz.bell

import cn.edu.bnuz.bell.wx.AuthTokenService
import cn.edu.bnuz.bell.wx.TestJssdkService
import grails.converters.JSON
import org.springframework.beans.factory.annotation.Value

class ConfigController {
    TestJssdkService testJssdkService
    AuthTokenService authTokenService
    @Value('${bell.appId}')
    String appID
    @Value('${bell.appSecret}')
    String appSecret

    def index() { }

    def save() {
        String signUrl = request.getParameter("signUrl")

        String accessToken= getAccessToken()
        String jsapi_ticket = getJsApiTicket(accessToken)
        render (testJssdkService.generateWxTicket(jsapi_ticket, signUrl) as JSON)
    }

    private getAccessToken() {
        def accessToken = authTokenService.findToken(appID, appSecret)
        if (!accessToken) {
            String path = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=${appID}&&secret=${appSecret}"
            def tokenFromHttp = getFromHttp(path)
            authTokenService.saveAccessToken(tokenFromHttp, appID, appSecret)
            accessToken = tokenFromHttp.access_token
        }
        return accessToken
    }

    static getFromHttp(String path) {
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
                println strs.get(0)
                return JSON.parse(strs.get(0))
            }
        }
        return null
    }

    private getJsApiTicket(String accessToken) {
        def ticket = authTokenService.findTicketByToken(accessToken)
        if (!ticket) {
            String apiTicketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=${accessToken}&type=jsapi"
            def jsapiTicket =  getFromHttp(apiTicketUrl)
            if (jsapiTicket) {
                ticket = jsapiTicket.ticket
                authTokenService.saveJsApiTicket(accessToken, ticket)
            }
        }
        return ticket

    }
}
