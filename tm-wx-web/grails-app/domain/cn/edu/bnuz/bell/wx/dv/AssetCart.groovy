package cn.edu.bnuz.bell.wx.dv

class AssetCart {
    Long assetId
    String userId
    String name

    static mapping = {
        comment '设备购物车'
        table schema: 'tm_asset'
        id generator: 'identity', comment: 'ID'
        userId  length: 5, comment: '所有人'
        assetId comment: '设备Id'
        name comment: '购物车名称'
    }

    static constraints = {
        name nullable: true
    }
}
