package cn.edu.bnuz.bell


import cn.edu.bnuz.bell.wx.AuthService
import cn.edu.bnuz.bell.wx.BindUserService
import cn.edu.bnuz.bell.wx.ScoreService

class ScoreController {
    ScoreService scoreService
    BindUserService bindUserService
    AuthService authService

    def index(String code) {
        def openid = authService.findOpenId(code)
        if (bindUserService.checkOpenId(openid)) {
            def scores = scoreService.findScoresByOpenId(openid)
            def terms = scoreService.distinctTerm(scores)
            return ([scores: scores, terms: terms])
        } else {
            response.sendRedirect("/student?act=bindUser")
        }

    }
}
