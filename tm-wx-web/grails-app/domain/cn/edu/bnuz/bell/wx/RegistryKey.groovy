package cn.edu.bnuz.bell.wx

class RegistryKey {
    NewFireUser user
    Integer key
    Long createdTime
    Long expiresIn
    static mapping = {
        id generator: 'identity', comment: '无意义Id'
        table schema: 'tm_wx'
        key comment: '验证码'
        expiresIn   comment: '有效时间'
        createdTime comment: '获取时间'
        user comment: '生成者'
    }
    static constraints = {
        user nullable: true
    }
}
