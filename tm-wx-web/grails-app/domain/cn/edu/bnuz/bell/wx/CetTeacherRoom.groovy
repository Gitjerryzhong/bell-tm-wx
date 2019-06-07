package cn.edu.bnuz.bell.wx

class CetTeacherRoom {
    CetTeacher cetTeacher
    ExamRoom examRoom

    static belongsTo = [examRoom: ExamRoom]

    static mapping = {
        table schema: 'tm_wx'
        id generator: 'identity', comment: '无意义Id'
        cetTeacher comment: '监考老师'
        examRoom comment: '考场'
    }
    static constraints = {
        cetTeacher unique: 'examRoom'
    }
}
