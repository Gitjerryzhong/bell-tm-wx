package cn.edu.bnuz.bell

import cn.edu.bnuz.bell.wx.AuthTokenService
import grails.converters.JSON
import org.springframework.beans.factory.annotation.Value

class VoiceKeeperController {
    AuthTokenService authTokenService
    @Value('${bell.appId}')
    String appID
    @Value('${bell.appSecret}')
    String appSecret
    @Value('${bell.wxValidateFile}')
    String voiceFile

    def index() { }
    def save() {
        String serverId = request.getParameter("serverId")
        println serverId
        keepVoice(getAccessToken(), serverId)
        render([id: serverId]  as JSON)
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
            return conn.getInputStream()
        }
    }

    private keepVoice(String accessToken, String serverId) {
        String voicetUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=${accessToken}&media_id=${serverId}"
        InputStream inputStream = getFromHttp(voicetUrl)
        byte[] data = new byte[10240]
        int len = 0
        FileOutputStream fileOutputStream = null
        try {
            fileOutputStream = new FileOutputStream("${voiceFile}/${serverId}.amr")
            while ((len = inputStream.read(data)) != -1) {
                fileOutputStream.write(data, 0, len)
            }
        } catch (IOException e) {
            e.printStackTrace()
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close()
                } catch (IOException e) {
                    e.printStackTrace()
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close()
                } catch (IOException e) {
                    e.printStackTrace()
                }
            }
        }
    }

}
