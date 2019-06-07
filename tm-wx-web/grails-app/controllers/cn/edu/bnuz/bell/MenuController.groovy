package cn.edu.bnuz.bell


import cn.edu.bnuz.bell.util.Menu
import grails.converters.JSON
import org.springframework.beans.factory.annotation.Value
import cn.edu.bnuz.bell.wx.AuthTokenService

class MenuController {
    AuthTokenService authTokenService

    @Value('${bell.host}')
    String host
    @Value('${bell.appId}')
    String appID
    @Value('${bell.appSecret}')
    String appSecret

    def index() {
        if (false) {
            def menu = new Menu()
            menu.menuText = menu.menuText.replaceAll("http://es-test.bnuz.edu.cn", host)
            menu.create(getAccessToken())
        }
    }

    private getAccessToken() {
        def accessToken = authTokenService.findToken(appID, appSecret)
        if (!accessToken) {
            String path = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=${appID}&&secret=${appSecret}"
            def tokenFromHttp = getAccessTokenFromHttp(path)
            authTokenService.saveAccessToken(tokenFromHttp, appID, appSecret)
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
