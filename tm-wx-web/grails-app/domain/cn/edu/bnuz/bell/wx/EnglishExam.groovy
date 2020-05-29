package cn.edu.bnuz.bell.wx

class EnglishExam {
    String studentId
    String type
    String flag
    String dateExam
    String teacherName
    String teacherPhone
    static mapping = {
        table schema: 'tm_wx'
        id generator: 'identity', comment: '无意义Id'
        studentId type: 'text',  comment: '用户'
        flag type: 'text', comment: '确认结果'
        type comment: '报名类别'
        dateExam type: 'text', comment: '考试时间'
        teacherName type: 'text', comment: '监考老师'
        teacherPhone type: 'text', comment: '老师电话'
    }

    static constraints = {
        studentId unique: true
        dateExam nullable: true
        teacherName nullable: true
        teacherPhone nullable: true
    }
}
