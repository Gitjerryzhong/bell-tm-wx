package cn.edu.bnuz.bell.wx

class Words {
    String openId
    String word
    Date dateCreated
    Boolean activited

    static mapping = {
        table schema: 'tm_wx'
        id generator: 'identity', comment: '无意义Id'
        openId type: 'text', comment: '用户微信id'
        word type: 'text', comment: '用户消息内容'
        dateCreated comment: '接收时间'
        activited comment: '有效关键字'
    }
}
