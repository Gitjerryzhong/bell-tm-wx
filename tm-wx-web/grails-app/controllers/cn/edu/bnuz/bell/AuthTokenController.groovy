package cn.edu.bnuz.bell

import cn.edu.bnuz.bell.wx.MessageService
import org.springframework.beans.factory.annotation.Value
import cn.edu.bnuz.bell.wx.AuthTokenService

class AuthTokenController {
    AuthTokenService authTokenService
    MessageService messageService
    @Value('${bell.wxValidateFile}')
    String filesPath

    def index() {
        def token = 'bnuz123'
        def arr = [token, params.timestamp, params.nonce]
        arr = arr.sort()
        StringBuffer sb = new StringBuffer()
        for(String s: arr) {
            sb.append(s)
        }
        def temp = authTokenService.getSha1(sb.toString())
        if (temp == params.signature) {
            render params.echostr
        } else {
            render('Error!!!')
        }
    }

    def getText() {
        def desFileName = 'MP_verify_Y7hOKkWY0qdulcvt.txt'
        File file = new File(filesPath, "${desFileName}")
        def bytes = file.bytes
        response.contentType = URLConnection.guessContentTypeFromName(file.getName())
        response.outputStream << bytes
        response.outputStream.flush()
    }

    def save() {
        def xml = new XmlParser().parse(request.reader)
        def type = xml.MsgType.text()
        def msg
        if (type == 'text') {
            msg = messageService.getTextMsg(xml)
            messageService.log(msg)
            def answer = messageService.getAnswer(msg)
            if (answer) {
                msg.content = answer
            } else {
                def key = msg.content
                msg.content = null
                if (!key.isLong()) {
                    key = key.toLowerCase()
                    def answers = messageService.findAnswers(key)
                    msg.content = ''
                    answers.eachWithIndex {a, index ->
                        msg.content = "${msg.content == '' ? '您是不是想咨询：' : msg.content}\n${index + 1}. ${a.title}"
                    }
                    if (msg.content != '') {
                        msg.content += '\n 请输入序号选择。'
                    }
                } else {
                    def word = messageService.getActiveWord(msg)
                    if (word) {
                        def answers = messageService.findAnswers(word)
                        if (answers) {
                            if (answers.size() <= key.toInteger() - 1 || key.toInteger() < 1) {
                                msg.content = '请输入相应序号。'
                            } else {
                                msg.content = answers.get(key.toInteger() - 1).description
                            }
                        }
                    }
                }
                if (!msg.content) {
                    msg.content = '感谢您的留言！'
                }
            }
            render msg.send()
        } else if (type == 'image') {
            msg = messageService.getImageMsg(xml)
            render msg.send()
        } else if (type == 'voice') {
            msg = messageService.getVoiceMsg(xml)
            render msg.send()
        } else {
            msg = messageService.getTextMsg(xml)
            msg.content = '感谢您的留言！'
            render msg.send()
        }

    }
}
