package cn.edu.bnuz.bell.wx

class Theme {
    String title
    Boolean viewAble
    Boolean attendAble

    static hasMany = [talking: Talking, permission: ThemePermission]

    static mapping = {
        id generator: 'identity', comment: '无意义Id'
        table schema: 'tm_wx'
        title type: 'text', comment: '发言人'
        attendAble comment: '可参与'
        viewAble comment: '可浏览'
    }

    static constraints = {
    }
}
