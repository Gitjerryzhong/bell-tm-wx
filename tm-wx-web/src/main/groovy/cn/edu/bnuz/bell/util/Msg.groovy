package cn.edu.bnuz.bell.util

abstract  class Msg {
    String toUserName
    String fromUserName
    String createTime
    String msgType
    String msgId
    abstract send()
}
