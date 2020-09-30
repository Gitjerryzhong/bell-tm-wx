package cn.edu.bnuz.bell.wx

class AccessLog {
    String openId
    Date dateCreated
    Boolean isSubscribe
    static mapping = {
        table schema: 'tm_wx'
        id generator: 'identity', comment: '无意义Id'
        openId type: 'text', comment: '用户openId'
        dateCreated comment: '登录时间'
        isSubscribe comment: '是否关注'
    }
}
