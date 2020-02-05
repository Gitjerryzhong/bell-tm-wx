package cn.edu.bnuz.bell

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus

class NewFireController {
    @Value('${bell.host}')
    String host
    @Value('${bell.appId}')
    String appId
    @Value('${bell.appSecret}')
    String appSecret

    def index() {
        // 回调地址
        String backUrl = "${host}/register"
        switch (params.act) {
            case 'register':
                backUrl = "${host}/register"
                break
            case 'theme':
                backUrl = "${host}/theme"
                break
            case 'talking':
                backUrl = "${host}/talking?themeId=${params.themeId}"
                break
            default:
                render status: HttpStatus.BAD_REQUEST
        }
        println backUrl
        println appId
        // 授权页面地址
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId +
                "&redirect_uri=" + URLEncoder.encode(backUrl, 'UTF-8') +
                "&response_type=code" +
                "&scope=snsapi_userinfo" +
                "&state=STATE#wechat_redirect"
        response.sendRedirect(url)
    }
}
