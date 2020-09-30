package cn.edu.bnuz.bell

import cn.edu.bnuz.bell.wx.AuthService
import cn.edu.bnuz.bell.wx.QualifyExamService
import grails.converters.JSON

class QualifyExamController {
    AuthService authService
    QualifyExamService qualifyExamService

    def index(String code) {
        println code
        def openid = authService.findOpenId(code)
        if (!openid) {
            render view:"/message", model: [message: "非法操作！"]
        }
        return([openid: openid, code: code])
    }

    def getExamInfo(String openId, String examId, String password) {
        if (!openId) {
            render view:"/message", model: [message: "非法操作！请先关注公众号。"]
        } else if (examId != null && password != null) {
            render(qualifyExamService.getExamInfo(examId, password) as JSON)
        }
    }
}
