package cn.edu.bnuz.bell.wx

/**
 * 考试
 */
class Exam {
    String name
    String examTime
    String paperTime

    static mapping = {
        table schema: 'tm_wx'
        id generator: 'identity', comment: '无意义Id'
        examTime type: 'text', comment: '考试时间'
        paperTime type: 'text', comment: '领劵时间'
    }
}
