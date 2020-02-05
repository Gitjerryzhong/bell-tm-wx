package cn.edu.bnuz.bell.wx

class ThemePermission {
    NewFireUser user
    String type
    Boolean speakAble
    Boolean viewAble
    Theme theme
    static belongsTo = [theme: Theme]

    static mapping = {
        id generator: 'identity', comment: '无意义Id'
        table schema: 'tm_wx'
        user comment: '发言人'
        speakAble comment: '可发言'
        viewAble comment: '可浏览'
        theme comment: '所属主题'
        type type: 'text', comment: '用户类型'
    }

    static constraints = {
    }
}
