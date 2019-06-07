package cn.edu.bnuz.bell.wx

class Answers {
    /**
     * 编码
     * A01
     * -==
     * | |
     * \ ----序号
     *  ----类别
     */
    String id
    /**
     * 主关键字
     */
    String title
    /**
     * 解释
     */
    String description

    static mapping = {
        table schema: 'tm_wx'
        id      generator: 'assigned', length: 3, comment: '编号'
        title  type: 'text', comment: '主关键字'
        description   type: 'text', comment: '解释'
    }
}
