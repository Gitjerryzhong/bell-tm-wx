package cn.edu.bnuz.bell

import cn.edu.bnuz.bell.cmd.UserCommand
import cn.edu.bnuz.bell.wx.AuthService
import cn.edu.bnuz.bell.wx.BindCetTeacherService
import cn.edu.bnuz.bell.wx.BindUserService
import grails.converters.JSON

class BindCetUserController {
    BindCetTeacherService bindCetTeacherService
    AuthService authService

    def index(String code) {
        def openid = authService.findOpenId(code)
        def result = bindCetTeacherService.checkOpenId(openid)
        if (result != 'PASS') {
            render view:"/message", model: [message: result]
        } else {
            return([openid: openid, sms: authService.smsHost])
        }
    }

    def save(UserCommand cmd) {
        render ([state: bindCetTeacherService.bind(cmd)] as JSON)
    }
}
