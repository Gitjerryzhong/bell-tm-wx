package cn.edu.bnuz.bell.wx

class AccessToken {
    String token
    Long expiresIn
    String appId
    String appSecret
    Long createdTime
    static mapping = {
        id generator: 'identity', comment: '无意义Id'
        table schema: 'tm_wx'
        token       length: 300, comment: 'token'
        expiresIn   comment: '有效时间'
        appId       length: 60, comment: '用户ID'
        appSecret   length: 60, comment: '用户密钥'
        createdTime comment: '获取时间'
    }
}
