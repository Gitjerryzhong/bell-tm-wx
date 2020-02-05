package cn.edu.bnuz.bell

import cn.edu.bnuz.bell.wx.TestJssdkService

class TestJssdkController {


    def index() {
        return testJssdkService.generateWxTicket()
    }
}
