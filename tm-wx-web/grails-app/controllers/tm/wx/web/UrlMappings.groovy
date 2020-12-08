package tm.wx.web

class UrlMappings {

    static mappings = {
        "/message"(view:"/message")
        "/reportList"(view:"/reportList")
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
        "/delay"(resources: 'delay') {
            collection {
                "/remove"(controller: 'delay', action: 'remove', method: 'GET')
                "/getUserInfo"(controller: 'delay', action: 'getUserInfo', method: 'GET')
            }
        }
        "/bindNewPhone"(resources: 'bindNewPhone')
        "/score"(resources: 'score', includes: ['index'])
        "/levelExam"(resources: 'levelExam', includes: ['index'])
        "/takeSeat"(resources: 'takeSeat', includes: ['index', 'show'])
        "/assetViewer"(resources: 'assetViewer', includes: ['index'])
        "/makeUp"(resources: 'makeUp'){
            collection {
                "/otherMakeUp"(controller: 'makeUp', action: 'otherMakeUp', method: 'GET')
            }
        }
        "/qualifyExam"(resources: 'qualifyExam'){
            collection {
                "/getExamInfo"(controller: 'qualifyExam', action: 'getExamInfo', method: 'GET')
            }
        }
        "/degreeEnglish"(resources: 'degreeEnglish')
        "/smsValidate"(resources: 'smsValidate', includes: ['index'])
        "/"(resources: 'menu', includes: ['index'])
    }
}
