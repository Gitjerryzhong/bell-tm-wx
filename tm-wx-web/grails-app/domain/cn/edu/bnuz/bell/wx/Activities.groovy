package cn.edu.bnuz.bell.wx

/**
 * 活动
 */
class Activities {
    String name
    Date start
    Date deadline
    String groups
    String message

    static mapping = {
        table schema: 'tm_wx'
        id generator: 'identity', comment: '无意义Id'
        name  type: 'text', comment: '活动名'
        start  comment: '起始时间'
        deadline comment: '截至时间'
        groups type: 'text', comment: '分区方式'
        message type: 'text', comment: '警告信息'
    }
    static constraints = {
        groups nullable: true
        message nullable: true
    }
}
