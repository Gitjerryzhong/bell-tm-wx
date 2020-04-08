package cn.edu.bnuz.bell.cmd

import grails.validation.Validateable

class MakeUpCommand implements Validateable{
    String openId
    String phone
    String email
    String flags
}