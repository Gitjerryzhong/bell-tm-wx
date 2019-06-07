package tm.wx.web

class UrlMappings {

    static mappings = {
        "/message"(view:"/message")
        "/info"(view:"/info")
        "/student-menu"(view:"/student-info")
        "/teacher-menu"(view:"/teacher-info")
        "/*.txt"(controller: 'authToken', action: 'getText', method: 'GET')
        "500"(view:'/error')
        "404"(view:'/notFound')

        "/authTokens"(resources: 'authToken', includes: ['index', 'save'])
        "/student"(resources: 'student', includes: ['index'])
        "/teacher"(resources: 'teacher', includes: ['index'])
        "/bindUser"(resources: 'bindUser')
        "/bindCetUser"(resources: 'bindCetUser')
        "/bindNewPhone"(resources: 'bindNewPhone')
        "/score"(resources: 'score', includes: ['index'])
        "/levelExam"(resources: 'levelExam', includes: ['index'])
        "/takeSeat"(resources: 'takeSeat', includes: ['index', 'show'])
        "/smsValidate"(resources: 'smsValidate', includes: ['index'])
        "/"(resources: 'menu', includes: ['index'])
    }
}
