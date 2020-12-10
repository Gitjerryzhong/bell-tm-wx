package cn.edu.bnuz.bell.wx.dv

class DvAssetUser {
    String userId
    String roleId
    static mapping = {
        table schema: 'tm_wx'
    }
}
