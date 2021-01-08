package cn.edu.bnuz.bell.wx.dv

class DvAssetChangeLog {
    Long id
    Long assetId
    String name
    String brand
    String specs
    String parameter
    String supplier
    Date dateCreated
    String sake
    static mapping = {
        table schema: 'tm_wx'
    }
}
