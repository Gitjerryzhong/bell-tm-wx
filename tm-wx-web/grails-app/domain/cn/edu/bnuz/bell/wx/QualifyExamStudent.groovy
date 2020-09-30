package cn.edu.bnuz.bell.wx

class QualifyExamStudent {
    Integer id
    String examId
    String name
    String type
    String idNum
    String examPlace
    String examTime
    Integer roomId
    static mapping = {
        table schema: 'tm_wx', name: 'qualify_exam_student'
        id  generator: 'identity', comment: '无意义Id'
        examId type: 'text', comment: '考生号'
        name type: 'text', comment: '姓名'
        roomId  type: 'text', comment: '考场号'
        type type: 'text', comment: '考试类型'
        idNum type: 'text', comment: '身份证号'
        examPlace type: 'text', comment: '考点'
        examTime type: 'text', comment: '考试时间'
    }
}
