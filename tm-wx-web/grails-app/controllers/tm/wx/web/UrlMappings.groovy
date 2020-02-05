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

        "/config"(resources: 'config', includes: ['index', 'save'])
        "/voiceKeeper"(resources: 'voiceKeeper', includes: ['index', 'save'])
        "/newFire"(resources: 'newFire', includes: ['index'])
        "/register"(resources: 'register')
        "/theme"(resources: 'theme')
        "/talking"(resources: 'talking') {
            collection {
                "/search"(controller: 'talking', action: 'search', method: 'GET')
            }
        }
        "/test-menu"(view:"/test-info")


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
