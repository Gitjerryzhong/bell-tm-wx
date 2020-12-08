package cn.edu.bnuz.bell

import cn.edu.bnuz.bell.wx.AssetViewerService
import cn.edu.bnuz.bell.wx.AuthService
import javassist.tools.web.BadHttpRequest

class AssetViewerController {
    AuthService authService
    AssetViewerService assetViewerService

    def index(String code, Long assetId) {
        def openid = authService.findOpenId(code)
        def now = new Date()
        if (!openid) {
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
}
