package cn.edu.bnuz.bell.util


class VoiceMsg extends Msg{
    String mediaId

    def send() {
        def sw = new StringWriter()
        def xml = new groovy.xml.MarkupBuilder(sw)
        xml.xml(){
            ToUserName(fromUserName)
            FromUserName(toUserName)
            CreateTime(new Date())
            MsgType(msgType)
            Voice(){
                MediaId(mediaId)
            }
        }
        return sw
    }
}
