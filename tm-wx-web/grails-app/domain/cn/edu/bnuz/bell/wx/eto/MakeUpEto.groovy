package cn.edu.bnuz.bell.wx.eto

class MakeUpEto implements Serializable{
    String xn
    String xq
    String courseId
    String studentId
    String flag
    String graduation
    String times
    String studentName
    String courseName
    String credit
    String property
    String departmentName
    String makeUpTime
    String grade

    static mapping = {
        table       name: 'et_make_up', schema: 'tm_wx'
        id composite: ['xn', 'xq', 'courseId', 'studentId']
    }
}
