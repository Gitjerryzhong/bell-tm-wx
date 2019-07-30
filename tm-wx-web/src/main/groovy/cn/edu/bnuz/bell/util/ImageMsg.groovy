package cn.edu.bnuz.bell.util

class ImageMsg extends Msg{
    String picUrl
    String mediaId

    def send() {
        def sw = new StringWriter()
        def xml = new groovy.xml.MarkupBuilder(sw)
        xml.xml(){
            ToUserName(fromUserName)
            FromUserName(toUserName)
            CreateTime(new Date())
            MsgType(msgType)
            Image(){
                MediaId(mediaId)
            }
        }
        return sw
    }
}
