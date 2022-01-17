package cn.edu.bnuz.bell.wx

import java.time.LocalDate

class TimeConfig {
    String bussinees
    LocalDate lowerDate
    LocalDate upperDate

    static mapping = {
        table schema: 'tm_wx'
        id generator: 'identity', comment: '无意义Id'
        bussinees type: 'text', comment: '无意义Id'
        lowerDate comment: '起始时间'
        upperDate comment: '结束时间'
    }

    static constraints = {
        bussinees unique: true
    }
}
