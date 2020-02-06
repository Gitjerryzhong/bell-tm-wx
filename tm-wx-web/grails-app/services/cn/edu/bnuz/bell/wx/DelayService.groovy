package cn.edu.bnuz.bell.wx

import cn.edu.bnuz.bell.cmd.ReportCommand
import cn.edu.bnuz.bell.wx.dv.DvUser
import grails.gorm.transactions.Transactional
import javassist.tools.web.BadHttpRequest

@Transactional
class DelayService {

    def getUserInfo(String openId) {
        def result = PostInfo.executeQuery'''
select new map(
u.id as userId,
du.name as name,
u.phone as phone1,
i.phone as phone2,
i.seal as seal,
i.sealComment as sealComment,
i.address as address
)
from PostInfo i
right join i.user u,
DvUser du
where u.openId = :openId and du.id = u.id
''', [openId: openId]
        return result ? result[0] : []
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
order by r.type, r.id
''', [openId: openId]
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
        def result = Report.executeQuery'''
select distinct new map(
u.id as userId,
du.name as name,
i.phone as phone,
i.seal as seal,
i.sealComment as sealComment,
i.address as address
)
from Report r
join r.user u 
join u.postInfo i,
DvUser du
where to_char(r.dateCreated, 'YYYY-MM-DD') = to_char(now(), 'YYYY-MM-DD') and du.id = u.id
'''
        result.each {item ->
            item['reports'] = findAllReportByUser(item.userId)
        }
        return result
    }

    def findAllReportByUser(String userId) {
        Report.executeQuery'''
select distinct new map(
r.id as id,
r.type as type,
r.ps as ps
)
from Report r
join r.user u 
where to_char(r.dateCreated, 'YYYY-MM-DD') = to_char(now(), 'YYYY-MM-DD') and u.id = :userId
''', [userId: userId]
    }
}
