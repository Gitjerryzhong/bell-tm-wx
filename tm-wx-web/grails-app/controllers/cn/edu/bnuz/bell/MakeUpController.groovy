package cn.edu.bnuz.bell

import cn.edu.bnuz.bell.cmd.MakeUpCommand
import cn.edu.bnuz.bell.wx.Activities
import cn.edu.bnuz.bell.wx.AuthService
import cn.edu.bnuz.bell.wx.BindUserService
import cn.edu.bnuz.bell.wx.DelayService
import cn.edu.bnuz.bell.wx.MakeUpService
import grails.converters.JSON

class MakeUpController {
    AuthService authService
    MakeUpService makeUpService
    BindUserService bindUserService
    DelayService delayService

    def index(String code) {
        def openid = authService.findOpenId(code)
        if (bindUserService.checkOpenId(openid)) {
            def list = makeUpService.list(openid)
            def listForConfirm = list?.grep {
                it.flag == '0'
            }
            def user = delayService.getUserInfo(openid)
            Activities activity = Activities.findByName('MAKE UP')
            return ([
                    list: listForConfirm,
                    user: user,
                    sms: authService.smsHost,
                    expire: new Date().before(activity.start) || new Date().after(activity.deadline),
                    message: activity?.message,
                    permission: list?.size() > 0
            ])
        } else {
            response.sendRedirect("/student?act=bindUser")
        }
    }

    def save (MakeUpCommand cmd) {
        render ([state: makeUpService.update(cmd)] as JSON)
    }

    def otherMakeUp(String openId) {
        render (makeUpService.other(openId) as JSON)
    }
}
