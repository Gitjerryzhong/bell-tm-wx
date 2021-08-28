package cn.edu.bnuz.bell.wx

import cn.edu.bnuz.bell.cmd.ReportCommand
import cn.edu.bnuz.bell.wx.dv.DvUser
import cn.edu.bnuz.bell.wx.eto.StudentAbroadEto
import cn.edu.bnuz.bell.wx.eto.StudentMinorEto
import grails.gorm.transactions.Transactional
import javassist.tools.web.BadHttpRequest
import cn.edu.bnuz.bell.wx.eto.StudentEto

import java.time.LocalDate;

@Transactional
class DelayService {

    def getUserInfo(String openId) {
        def result = PostInfo.executeQuery'''
select new map(
u.openId as openId,
u.id as userId,
u.phone as phone1,
i.phone as phone2,
i.seal as seal,
i.sealComment as sealComment,
i.address as address,
i.email as email
)
from PostInfo i
right join i.user u
where u.openId = :openId
''', [openId: openId]
        if (result) {
            def dvUser = DvUser.findById(result[0].userId)
            def stdEto = StudentEto.findById(result[0].userId)
            result[0]['name'] = dvUser ? dvUser.name : stdEto?.name
            result[0]['atSchool'] = stdEto?.atSchool == '是' && stdEto?.enroll == '有'
            def stdMinor = StudentMinorEto.findById(result[0].userId)
            def stdAbroad = StudentAbroadEto.findById(result[0].userId)
            result[0]['minor'] = stdMinor ? true : false
            result[0]['abroad'] = stdAbroad ? true : false
            result[0]['grade'] = stdEto?.grade
            result[0]['graduation'] = stdEto?.graduation
            return result[0]
        }
        return [:]
    }

    def create(ReportCommand cmd) {
        def user = User.findByOpenId(cmd.openId)
        if (!user) {
            throw new BadHttpRequest()
        }
        def postInfo = PostInfo.findByUser(user)
        if (postInfo) {
            postInfo.address = cmd.address
            postInfo.seal = cmd.seal
            postInfo.sealComment = cmd.sealComment
            postInfo.phone = cmd.phone
            postInfo.save()
        } else {
            postInfo = new PostInfo(
                    address: cmd.address,
                    seal: cmd.seal,
                    sealComment: cmd.sealComment,
                    phone: cmd.phone,
                    user: user
            )
            if(!postInfo.save()) {
                postInfo.errors.each {
                    println it
                }
            }
        }
        def report = new Report(
                user: user,
                type: cmd.type,
                ps: cmd.ps
        )
        if(!report.save()) {
            report.errors.each {
                println it
            }
        }
        return 'OK'
    }

    def list(String openId) {
        def date = LocalDate.now()
        def lastSaturday = date.plusDays(-(date.dayOfWeek.value + 1))
        Report.executeQuery'''
select new map(
r.id as id,
r.type as type,
r.ps as ps,
to_char(r.dateCreated, 'YYYY-MM-DD') = to_char(now(), 'YYYY-MM-DD') as removeAble
)
from Report r
join r.user u 
where u.openId = :openId
and r.dateCreated > to_date(:lastSaturday, 'YYYY-MM-DD')  
order by r.type, r.id
''', [openId: openId, lastSaturday: lastSaturday.toString()]
    }

    def remove(String openId, Long id) {
        def report = Report.load(id)
        if (report.user.openId != openId) {
            throw new BadHttpRequest()
        }
        report.delete()
        return 'OK'
    }

    def isHou(String openId) {
        def user = User.findByOpenId(openId)
        if (user) {
            def dvUser = DvUser.findById(user.id)
            return  dvUser ? dvUser.name == '侯彦宏' : false
        }
        return false
    }

    def listAll() {
        def date = LocalDate.now()
        def lastSaturday = date.plusDays(-(date.dayOfWeek.value + 1))
//        if (date.dayOfWeek.value == 6) {
//            lastSaturday = date.plusDays(-(date.dayOfWeek.value + 2))
//        }
        def result = Report.executeQuery'''
select distinct new map(
u.id as userId,
i.phone as phone,
i.seal as seal,
i.sealComment as sealComment,
i.address as address
)
from Report r
join r.user u 
join u.postInfo i
where r.dateCreated between to_date(:lastSaturday, 'YYYY-MM-DD') and to_date(:date, 'YYYY-MM-DD')
''', [lastSaturday: lastSaturday.toString(), date: date.toString()]
        result.each {item ->
            def dvUser = DvUser.findById(item.userId)
            def stdEto = StudentEto.findById(item.userId)
            item['name'] = dvUser ? dvUser.name : stdEto?.name
            item['reports'] = findAllReportByUser(item.userId, lastSaturday, date)
        }
        return result
    }

    def findAllReportByUser(String userId, LocalDate lastSaturday, LocalDate date) {
        Report.executeQuery'''
select distinct new map(
r.id as id,
r.type as type,
r.ps as ps
)
from Report r
join r.user u 
where r.dateCreated between to_date(:lastSaturday, 'YYYY-MM-DD') and to_date(:date, 'YYYY-MM-DD') and u.id = :userId
''', [userId: userId, lastSaturday: lastSaturday.toString(), date: date.toString()]
    }

}
