package cn.edu.bnuz.bell

import cn.edu.bnuz.bell.wx.AuthService
import cn.edu.bnuz.bell.wx.RegisterService
import cn.edu.bnuz.bell.wx.ThemeService

class ThemeController {
    AuthService authService
    RegisterService registerService
    ThemeService themeService

    def index(String code) {
        def openid = authService.findOpenId(code)
        if (!registerService.hasRegisted(openid)) {
            response.sendRedirect("/newFire?act=register")
        } else {
            return [list: themeService.list()]
        }
    }
}
