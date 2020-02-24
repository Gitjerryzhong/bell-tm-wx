package cn.edu.bnuz.bell.wx.eto

class StudentEto {
    String id
    String name
    String password
    String enroll
    String atSchool

    static mapping = {
        table       name: 'et_student', schema: 'tm_wx'
    }
}
