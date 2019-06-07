package cn.edu.bnuz.bell.wx

/**
 * 考场
 */
class ExamRoom {
    String place
    String name
    Exam exam
    ExamGroup group

    static hasMany = [cetTeacherRoom: CetTeacherRoom]

    static mapping = {
        table schema: 'tm_wx'
        id generator: 'identity', comment: '无意义Id'
        place type: 'text', comment: '地点'
        name type: 'text', comment: '考场名'
        exam comment: '考试'
        group comment: '资料组'
    }
}
