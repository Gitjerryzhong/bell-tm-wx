package cn.edu.bnuz.bell.wx.dv

class DvUser {
    String id
    String name
    String password
    Integer userType
    static mapping = {
        table schema: 'tm_wx'
        id      generator: 'assigned', length: 10, comment: '用户Id'
        name type: 'text', comment: '姓名'
        password  type: 'text', comment: '密码'
        userType comment: '用户类型'
    }
}
