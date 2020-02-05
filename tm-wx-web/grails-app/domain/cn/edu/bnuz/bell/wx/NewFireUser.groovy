package cn.edu.bnuz.bell.wx

class NewFireUser {
    String id
    String openId
    String name
    Date dateCreated
    String type
    Boolean enabled
    RegistryKey registryKey

    static mapping = {
        table schema: 'tm_wx'
        id      generator: 'assigned', length: 40, comment: '用户ID'
        openId  type: 'text', comment: '微信id'
        name   type: 'text', comment: '姓名'
        dateCreated comment: '绑定时间'
        type type: 'text', comment: '用户类型'
        enabled comment: '有效性'
        registryKey comment: '注册所用key'
    }

    static constraints = {
        name unique: true
        registryKey nullable: true
    }
}
