package cn.edu.bnuz.bell.wx

class EnglishExam {
    String studentId
    String type
    String flag
    static mapping = {
        table schema: 'tm_wx'
        id generator: 'identity', comment: '无意义Id'
        studentId type: 'text',  comment: '用户'
        flag type: 'text', comment: '确认结果'
        type comment: '报名类别'
    }

    static constraints = {
        studentId unique: true
    }
}
