package cn.edu.bnuz.bell.wx

class User {
    String id
    String openId
    String phone
    Date dateCreated
    /**
     * 历史绑定电话
     */
    String phoneUsed

    static hasOne = [postInfo: PostInfo]
    static mapping = {
        table schema: 'tm_wx'
        id      generator: 'assigned', length: 10, comment: '用户ID'
        openId  type: 'text', comment: '微信id'
        phone   type: 'text', comment: '手机号'
        phoneUsed type: 'text', comment: '历史绑定电话'
        dateCreated comment: '绑定时间'
    }

    static constraints = {
        phoneUsed nullable: true
        postInfo nullable: true
    }
}
