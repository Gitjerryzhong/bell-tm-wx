package cn.edu.bnuz.bell

import cn.edu.bnuz.bell.cmd.ReportCommand
import cn.edu.bnuz.bell.wx.AuthService
import cn.edu.bnuz.bell.wx.BindUserService
import cn.edu.bnuz.bell.wx.DelayService
import grails.converters.JSON

class DelayController {
    AuthService authService
    BindUserService bindUserService
    DelayService delayService

    def index(String code) {
        def openid = authService.findOpenId(code)
        if (bindUserService.checkOpenId(openid)) {
            if (delayService.isHou(openid)) {
                render view:"/reportList", model: [list: delayService.listAll()]
            } else {
                def user = delayService.getUserInfo(openid)
                if (user.userId.length() != 10) {
                    render (view: "/message", model: [message: "本功能只提供本校学生使用！"])
                }
//                return ([user: delayService.getUserInfo(openid), openid: openid, list: delayService.list(openid)])
            }
        } else {
            response.sendRedirect("/student?act=bindUser")
        }
    }

    def save (ReportCommand cmd) {
        render ([state: delayService.create(cmd), list: delayService.list(cmd.openId)] as JSON)
    }

    def remove(String openId, Long id) {
        render ([state: delayService.remove(openId, id), list: delayService.list(openId)] as JSON)
    }

    def getUserInfo(String openId) {
        render(delayService.getUserInfo(openId) as JSON)
    }
}
