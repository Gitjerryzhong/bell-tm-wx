package cn.edu.bnuz.bell.cmd

import grails.validation.Validateable

class NewFireUserCommand implements Validateable {
    String name
    Integer password
    String openId
}
