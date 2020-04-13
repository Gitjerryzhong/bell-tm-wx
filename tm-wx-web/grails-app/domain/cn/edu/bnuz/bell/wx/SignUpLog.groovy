package cn.edu.bnuz.bell.wx

class SignUpLog {
    User user
    Date dateCreated
    String flags
    /**
     * 报名类别：1，补考，2，学位英语
     */
    Integer type

    static mapping = {
        table schema: 'tm_wx'
        id generator: 'identity', comment: '无意义Id'
        user  comment: '用户'
        dateCreated comment: '申请时间'
        flags type: 'text', comment: '确认结果json'
        type comment: '报名类别'
    }
}
