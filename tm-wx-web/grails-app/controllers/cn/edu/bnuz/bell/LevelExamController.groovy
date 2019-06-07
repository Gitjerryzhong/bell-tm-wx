package cn.edu.bnuz.bell

import cn.edu.bnuz.bell.wx.AuthService
import cn.edu.bnuz.bell.wx.BindUserService
import cn.edu.bnuz.bell.wx.LevelExamService

class LevelExamController {
    LevelExamService levelExamService
    BindUserService bindUserService
    AuthService authService

    def index(String code) {
        def openid = authService.findOpenId(code)
        if (bindUserService.checkOpenId(openid)) {
            return levelExamService.list(openid)
        } else {
            response.sendRedirect("/student?act=bindUser")
        }

    }
}
