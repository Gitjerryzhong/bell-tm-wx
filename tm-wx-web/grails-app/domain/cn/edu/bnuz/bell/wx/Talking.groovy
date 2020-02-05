package cn.edu.bnuz.bell.wx

class Talking {
    NewFireUser user
    String content
    Date dateCreated
    Boolean deleted
    /**
     * 主题
     */
    Theme theme
    static belongsTo = [theme: Theme]
    static mapping = {
        id generator: 'identity', comment: '无意义Id'
        table schema: 'tm_wx'
        user comment: '发言人'
        content type: 'text', comment: '说话内容'
        dateCreated: comment: '时间'
        deleted: comment: '删除标志'
        theme: comment: '所属主题'
    }
}
