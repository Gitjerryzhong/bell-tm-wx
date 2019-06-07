package cn.edu.bnuz.bell.wx

class ExamGroup {
    String place
    CetTeacher captain

    static mapping = {
        table schema: 'tm_wx'
        id generator: 'identity', comment: '无意义Id'
        place type: 'text', comment: '资料组地点'
        captain comment: '组长'
    }
}
