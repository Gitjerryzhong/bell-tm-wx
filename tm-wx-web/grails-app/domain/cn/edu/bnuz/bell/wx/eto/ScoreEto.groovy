package cn.edu.bnuz.bell.wx.eto

class ScoreEto {
    String departmentName
    String xn
    Integer xq
    String courseId
    String studentId
    String studentName
    String courseName
    String credit
    String score
    Integer scoreNumber

    static mapping = {
        table       name: 'et_score', schema: 'tm_wx'
    }
}
