package cn.edu.bnuz.bell.cmd

import grails.validation.Validateable

class UserCommand implements Validateable {
    String studentId
    String password
    String openId
    String phone
}
