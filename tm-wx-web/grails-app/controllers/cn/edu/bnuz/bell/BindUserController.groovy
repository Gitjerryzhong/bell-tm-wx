package cn.edu.bnuz.bell

import cn.edu.bnuz.bell.cmd.UserCommand

import grails.converters.JSON
import cn.edu.bnuz.bell.wx.AuthService
import cn.edu.bnuz.bell.wx.BindUserService

class BindUserController {
    BindUserService bindUserService
    AuthService authService

    def index(String code, Integer userType) {
        def openid = authService.findOpenId(code)
        println openid
        if (bindUserService.checkOpenId(openid)) {
            render view:"/message", model: [message: "您已绑定，无需重复操作！"]
        } else {
            return([openid: openid, sms: authService.smsHost, hint: bindUserService.Hint[userType]])
        }
    }

    def show(String id) {
        render bindUserService.checkOpenId(id)
    }

    def save(UserCommand cmd) {
        render ([state: bindUserService.bind(cmd)] as JSON)
    }
}
