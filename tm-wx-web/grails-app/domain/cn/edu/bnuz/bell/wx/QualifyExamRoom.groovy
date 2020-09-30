package cn.edu.bnuz.bell.wx

class QualifyExamRoom {
    Integer id
    String place
    String roomNum
    String type
    String examTime
    Integer counts
    Integer startId
    Integer endId
    static mapping = {
        table schema: 'tm_wx'
        id  generator: 'identity', comment: '无意义Id'
        place type: 'text', comment: '地点'
        roomNum  type: 'text', comment: '考场号'
        type type: 'text', comment: '考试类型'
        examTime type: 'text', comment: '考试时间'
        counts comment: '考场安排人数'
        startId comment: '起始考生id'
        endId comment: '截止考生id'
    }
}
