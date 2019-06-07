package cn.edu.bnuz.bell.wx

class Seat {
    CetTeacher user
    Activities activity
    Integer row
    Integer col
    Date dateCreated

    static mapping = {
        table schema: 'tm_wx'
        id generator: 'identity', comment: '无意义Id'
        user  comment: '用户'
        activity comment: '活动'
        row  comment: '行'
        col comment: '列'
        dateCreated comment: '绑定时间'
    }

    static constraints = {
        user unique: 'activity'
    }
}
