package cn.edu.bnuz.bell

import cn.edu.bnuz.bell.cmd.SeatCommand
import cn.edu.bnuz.bell.wx.Activities
import cn.edu.bnuz.bell.wx.AuthService
import cn.edu.bnuz.bell.wx.TakeSeatService
import javassist.tools.web.BadHttpRequest

class TakeSeatController {
    AuthService authService
    TakeSeatService takeSeatService

    def index(String code, SeatCommand cmd) {
        def openid = authService.findOpenId(code)
        def activity = Activities.load(cmd.activityId)
        def now = new Date()
        if (!openid || !activity) {
            throw new BadHttpRequest()
        }

        if(activity.start > now || activity.deadline < now) {
            render view:"/message", model: [message: "活动已失效！"]
        } else if (!cmd.row || !cmd.col) {
            render view:"/message", model: [message: "无座！"]
        } else {
            def result = takeSeatService.save(openid, cmd)
            if (result == 'FAIL') {
                response.sendRedirect('/teacher?act=bindUser')
            } else {
                render view:"/message", model: [message: "${result}"]
            }
        }

    }

    def show(String code, Long id) {
        def openid = authService.findOpenId(code)
        if (!openid) {
            render view:"/message", model: [message: "非法操作！请按正确的步骤操作。"]
        } else {
            def examInfo = takeSeatService.getExamInfo(openid)
            if (examInfo) {
                return examInfo
            } else {
                response.sendRedirect('/teacher?act=bindCetUser')
            }
        }
    }
}
