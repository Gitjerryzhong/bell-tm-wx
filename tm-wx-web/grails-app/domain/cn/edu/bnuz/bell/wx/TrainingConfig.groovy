package cn.edu.bnuz.bell.wx
/**
 * 监考培训
 */
class TrainingConfig {
    String time1
    String place1
    String time2
    String place2

    static mapping = {
        table schema: 'tm_wx'
        id generator: 'identity', comment: '无意义Id'
        time1 type: 'text', comment: '培训时间1'
        place1 type: 'text', comment: '培训地点1'
        time2 type: 'text', comment: '培训时间2'
        place2 type: 'text', comment: '培训地点2'
    }
}
