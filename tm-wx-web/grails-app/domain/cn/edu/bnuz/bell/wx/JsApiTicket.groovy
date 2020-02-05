package cn.edu.bnuz.bell.wx

class JsApiTicket {
    String token
    Long expiresIn
    Long createdTime
    String ticket
    static mapping = {
        id generator: 'identity', comment: '无意义Id'
        table schema: 'tm_wx'
        token       length: 300, comment: 'token'
        expiresIn   comment: '有效时间'
        createdTime comment: '获取时间'
        ticket type: 'text', comment: '调用微信JS接口的临时票据'
    }
}
