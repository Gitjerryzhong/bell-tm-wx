package cn.edu.bnuz.bell

import cn.edu.bnuz.bell.cmd.TalkingCommand
import cn.edu.bnuz.bell.wx.AuthService
import cn.edu.bnuz.bell.wx.RegisterService
import cn.edu.bnuz.bell.wx.TalkingService
import grails.converters.JSON

class TalkingController {
    AuthService authService
    RegisterService registerService
    TalkingService talkingService

    def index(String code, Long themeId) {
        def openid = authService.findOpenId(code)
        if (!registerService.hasRegisted(openid)) {
            response.sendRedirect("/newFire?act=register")
        } else {
            return [talking: talkingService.list(openid, themeId), openid: openid, themeId: themeId]
        }
    }

    def save(TalkingCommand cmd) {
        println cmd.content
        render(talkingService.save(cmd) as JSON)
    }

    def search(String openId, Long themeId, String q) {
        println q
        if (openId && q) {
            render (talkingService.search(openId, themeId, q) as JSON)
        }
    }
}
