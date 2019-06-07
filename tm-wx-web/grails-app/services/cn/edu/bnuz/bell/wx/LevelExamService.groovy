package cn.edu.bnuz.bell.wx

import cn.edu.bnuz.bell.wx.eto.LevelExamEto
import grails.gorm.transactions.Transactional

@Transactional
class LevelExamService {

    def list(String openId) {
        def user = User.findByOpenId(openId)
        if (user) {
            List<Map> scores = LevelExamEto.executeQuery'''
select distinct new map(
    xn as xn,
    xq as xq,
    examName as examName,
    dateOfExam as dateOfExam,
    score as score,
    examId as examId,
    scoreListening as scoreListening,
    scoreReading as scoreReading,
    scoreWriting as scoreWriting,
    scoreComprehensive as scoreComprehensive,
    scoreSpeaking as scoreSpeaking,
    examSpeakingId as examSpeakingId,
    certificateId as certificateId
) from LevelExamEto
where studentId = :userId
order by xn, xq, examName, dateOfExam desc
''', [userId: user.id]
            def terms = []
            scores.each {
                terms += [xn: it.xn, xq: it.xq]
            }
            return [scores: scores, terms: terms.unique()]
        } else {
            return [scores: [], terms: []]
        }
    }
}
