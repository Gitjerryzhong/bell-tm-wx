package cn.edu.bnuz.bell.cmd

import grails.validation.Validateable

class DegreeEnglishCommand implements Validateable{
    String openId
    String phone
    String email
    String flag
}