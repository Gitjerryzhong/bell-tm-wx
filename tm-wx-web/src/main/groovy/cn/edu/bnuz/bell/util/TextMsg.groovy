package cn.edu.bnuz.bell.util

class TextMsg extends Msg{
    String content
    def send() {
        def sw = new StringWriter()
        def xml = new groovy.xml.MarkupBuilder(sw)
        xml.xml(){
            ToUserName(fromUserName)
            FromUserName(toUserName)
            CreateTime(new Date())
            MsgType(msgType)
            Content(content)
        }
        return sw
    }
}
