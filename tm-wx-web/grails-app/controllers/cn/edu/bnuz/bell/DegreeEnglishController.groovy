package cn.edu.bnuz.bell

import cn.edu.bnuz.bell.cmd.DegreeEnglishCommand
import cn.edu.bnuz.bell.wx.AuthService
import cn.edu.bnuz.bell.wx.BindUserService
import cn.edu.bnuz.bell.wx.DegreeEnglishService
import grails.converters.JSON

class DegreeEnglishController {
    AuthService authService
    DegreeEnglishService degreeEnglishService
    BindUserService bindUserService

    def index(String code) {
        def openid = authService.findOpenId(code)
        if (bindUserService.checkOpenId(openid)) {
            def user = degreeEnglishService.getUserInfo(openid)
            return ([user: user, sms: authService.smsHost])
        } else {
            response.sendRedirect("/student?act=bindUser")
        }
    }

    def save (DegreeEnglishCommand cmd) {
        render ([state: degreeEnglishService.update(cmd)] as JSON)
    }
}
