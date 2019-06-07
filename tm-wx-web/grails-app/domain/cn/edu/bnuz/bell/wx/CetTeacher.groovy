package cn.edu.bnuz.bell.wx

/**
 * 四六级监考老师
 */
class CetTeacher {
    String teacherId
    String name
    String phone
    String department
    String openId
    Boolean checked

    static mapping = {
        table schema: 'tm_wx'
        id      generator: 'identity', comment: '无意义Id'
        teacherId type: 'text', comment: '工号'
        name  type: 'text', comment: '姓名'
        phone   type: 'text', comment: '手机号'
        department type: 'text', comment: '部门'
        openId  type: 'text', comment: '微信id'
        checked comment: '已看聘书'
    }

    static constraints = {
        teacherId nullable: true
        openId nullable: true
        checked nullable: true
    }
}
