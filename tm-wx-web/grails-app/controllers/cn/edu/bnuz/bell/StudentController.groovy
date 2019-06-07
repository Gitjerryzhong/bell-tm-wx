package cn.edu.bnuz.bell


import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus

class StudentController {
    @Value('${bell.host}')
    String host
    @Value('${bell.appId}')
    String appId
    @Value('${bell.appSecret}')
    String appSecret

    def index() {
        // 回调地址
        String backUrl = "${host}/bindUser?userType=2"
        switch (params.act) {
            case 'bindUser':
                backUrl = "${host}/bindUser?userType=2"
                break
            case 'score':
                backUrl = "${host}/score"
                break
            case 'levelExam':
                backUrl = "${host}/levelExam"
                break
            case 'bindNewPhone':
                backUrl = "${host}/bindNewPhone?userType=2"
                break
            default:
                render status: HttpStatus.BAD_REQUEST
        }
        // 授权页面地址
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId +
                "&redirect_uri=" + URLEncoder.encode(backUrl, 'UTF-8') +
                "&response_type=code" +
                "&scope=snsapi_userinfo" +
                "&state=STATE#wechat_redirect"
        response.sendRedirect(url)
    }
}
