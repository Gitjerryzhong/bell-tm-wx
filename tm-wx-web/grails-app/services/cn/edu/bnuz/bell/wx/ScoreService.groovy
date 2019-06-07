package cn.edu.bnuz.bell.wx

import cn.edu.bnuz.bell.wx.User
import cn.edu.bnuz.bell.wx.eto.ScoreEto
import grails.gorm.transactions.Transactional

@Transactional
class ScoreService {

    def findScoresByOpenId(String openId) {
        def user = User.findByOpenId(openId)
        if (user) {
            ScoreEto.executeQuery'''
select new map(
    xn as xn,
    xq as xq,
    courseName as courseName,
    credit as credit,
    score as score,
    scoreNumber as scoreNumber
) from ScoreEto
where studentId = :userId
order by courseId desc
''', [userId: user.id]
        } else {
            return null
        }
    }

    def countCredits(List<Map> scores) {
        BigDecimal sum = 0
        scores.each { item ->
            if (item.scoreNumber >= 60) {
                sum += (item.credit as BigDecimal)
            }
        }
        return sum
    }

    def distinctTerm(List<Map> scores) {
        def terms = []
        scores.each {
            terms += [xn: it.xn, xq: it.xq]
        }
        return terms.unique()
    }
}
