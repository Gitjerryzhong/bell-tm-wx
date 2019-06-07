package cn.edu.bnuz.bell.cmd

import grails.validation.Validateable

class SeatCommand implements Validateable{
    Long activityId
    Integer row
    Integer col
}
