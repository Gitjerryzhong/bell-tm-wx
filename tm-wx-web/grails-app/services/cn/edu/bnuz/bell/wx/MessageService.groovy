package cn.edu.bnuz.bell.wx

import cn.edu.bnuz.bell.util.ImageMsg
import cn.edu.bnuz.bell.util.TextMsg
import grails.gorm.transactions.Transactional

@Transactional
class MessageService {

    def getTextMsg(webData) {
        if (!webData) {
            return null
        }
        return new TextMsg(
                toUserName: webData.ToUserName.text(),
                fromUserName: webData.FromUserName.text(),
                msgType: webData.MsgType.text(),
                createTime: webData.CreateTime.text(),
                msgId: webData.MsgId.text(),
                content: webData.Content.text()
        )
    }

    def getImageMsg(webData) {
        return new ImageMsg(
                toUserName: webData.ToUserName.text(),
                fromUserName: webData.FromUserName.text(),
                msgType: webData.MsgType.text(),
                createTime: webData.CreateTime.text(),
                msgId: webData.MsgId.text(),
                picUrl: webData.PicUrl.text(),
                mediaId: webData.MediaId.text()
        )
    }

    def log(TextMsg msg) {
        Words word = new Words(
                openId: msg.fromUserName,
                word: msg.content,
                dateCreated: new Date(),
                activited: !msg.content.isLong() && msg.content.length() >= 2
        )
        word.save()
    }

    def getActiveWord(TextMsg msg) {
        def now = new Date()
        def lastMinute = now[Calendar.MINUTE] - 20
        now.set(minute: lastMinute)
        def result = Words.executeQuery'''
select word from Words where openId = :openId and dateCreated > :deadLine and activited is true order by dateCreated desc
''', [openId: msg.fromUserName, deadLine: now], [max: 1]
        return result ? result[0] : null
    }

    def getAnswer(TextMsg msg) {
        def result = Answers.executeQuery'''
select description from Answers where id = :q or title = :q
''', [q: msg.content.toUpperCase()], [max: 1]
        return result ? result[0] : null
    }

    def findAnswers(String key) {
        QuestionKey.executeQuery'''
select distinct a
from QuestionKey q
join q.answer a
where :key like concat('%', q.key, '%') 
order by a.id
''', [key: key]
    }
}
