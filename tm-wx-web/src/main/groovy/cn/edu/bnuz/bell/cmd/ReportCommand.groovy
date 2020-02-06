package cn.edu.bnuz.bell.cmd

import grails.validation.Validateable

class ReportCommand implements Validateable{
    String type
    Integer ps
    String openId
    String phone
    String sealComment
    Boolean seal
    String address
}
