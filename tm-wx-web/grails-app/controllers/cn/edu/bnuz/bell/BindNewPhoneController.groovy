package cn.edu.bnuz.bell

import cn.edu.bnuz.bell.cmd.UserCommand

import cn.edu.bnuz.bell.wx.User
import grails.converters.JSON
import cn.edu.bnuz.bell.wx.AuthService
import cn.edu.bnuz.bell.wx.BindUserService

class BindNewPhoneController {
    BindUserService bindUserService
    AuthService authService

    def index(String code, Integer userType) {
        def openid = authService.findOpenId(code)
        User user = User.findByOpenId(openid)
        if (user) {
            return ([userId: user.id, openid: openid, sms: authService.smsHost, hint: bindUserService.Hint[userType]] )
        } else {
            response.sendRedirect("/${userType == 1 ? 'teacher' : 'student'}?act=bindUser")
        }
    }

    def save(UserCommand cmd) {
        render ([state: bindUserService.bindNewPhone(cmd)] as JSON)
    }
}
