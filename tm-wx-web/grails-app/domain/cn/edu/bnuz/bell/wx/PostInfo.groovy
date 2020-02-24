package cn.edu.bnuz.bell.wx

class PostInfo {
    User user
    String phone
    Boolean seal
    String sealComment
    String address
    static belongsTo = [user: User]
    static mapping = {
        table schema: 'tm_wx'
        id generator: 'identity', comment: '无意义Id'
        user  comment: '用户'
        seal comment: '是否密封'
        sealComment type: 'text', comment: '密封要求'
        address type: 'text', comment: '邮寄地址'
    }
    static constraints = {
        user unique: true
        sealComment nullable: true
    }
}
