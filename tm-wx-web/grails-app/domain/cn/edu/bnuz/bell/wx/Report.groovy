package cn.edu.bnuz.bell.wx

class Report {
    User user
    String type
    Integer ps
    Date dateCreated

    static mapping = {
        table schema: 'tm_wx'
        id generator: 'identity', comment: '无意义Id'
        user  comment: '用户'
        type  type: 'text', comment: '文件类型'
        ps comment: '份数'
        dateCreated comment: '申请时间'
    }

    static constraints = {
    }
}
