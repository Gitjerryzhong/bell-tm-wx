package cn.edu.bnuz.bell.cmd

import grails.validation.Validateable

class TalkingCommand implements Validateable {
    String content
    String openId
    Long themeId
}
