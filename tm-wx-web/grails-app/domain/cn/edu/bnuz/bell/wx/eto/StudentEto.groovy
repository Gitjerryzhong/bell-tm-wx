package cn.edu.bnuz.bell.wx.eto

class StudentEto {
    String id
    String name
    String password
    String enroll
    String atSchool
    Integer grade
    String major
    String department
    String graduation

    static mapping = {
        table       name: 'et_student', schema: 'tm_wx'
    }
}
