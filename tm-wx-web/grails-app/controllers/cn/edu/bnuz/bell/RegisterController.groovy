package cn.edu.bnuz.bell

import cn.edu.bnuz.bell.cmd.NewFireUserCommand
import cn.edu.bnuz.bell.wx.AuthService
import cn.edu.bnuz.bell.wx.RegisterService
import grails.converters.JSON

class RegisterController {
    AuthService authService
    RegisterService registerService

    def index(String code) {
        def openid = authService.findOpenId(code)
        println "openid=${openid}"
        if (registerService.isAdmin(openid)) {
            return [openid: openid, key: registerService.registryKey]
        } else if (registerService.hasRegisted(openid)) {
            render view:"/message", model: [message: '您已注册']
        } else {
            return([openid: openid])
        }

    }

    def save(NewFireUserCommand cmd) {
        render ([state: registerService.regist(cmd)] as JSON)
    }
}
