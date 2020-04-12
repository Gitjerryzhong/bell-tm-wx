package cn.edu.bnuz.bell.wx

import cn.edu.bnuz.bell.cmd.MakeUpCommand
import cn.edu.bnuz.bell.wx.eto.MakeUpEto
import grails.converters.JSON
import grails.gorm.transactions.Transactional

@Transactional
class MakeUpService {

    def list(String openId) {
        def user = User.findByOpenId(openId)
        if (user) {
            MakeUpEto.executeQuery'''
select new map(
    xn as xn,
    xq as xq,
    studentId as studentId,
    courseId as courseId,
    courseName as courseName,
    credit as credit,
    property as property,
    departmentName as departmentName,
    flag as flag,
    makeUpTime as makeUpTime
) from MakeUpEto
where xn='2019-2020' and xq = '2' and studentId = :userId and flag = '0'
''', [userId: user.id]
        } else {
            return null
        }
    }

    def update(MakeUpCommand cmd) {
        def user = User.findByOpenId(cmd.openId)
        if (!user) {
            return 'FAIL'
        }
        def flags = JSON.parse(cmd.flags)
        if (flags && flags.size()) {
            flags.each{
                MakeUpEto.executeUpdate'''
update MakeUpEto set flag=:flag
where xn = '2019-2020' and xq = '2' and studentId = :studentId and flag='0' and courseId = :courseId
''', [flag: it.flag as String, studentId: user.id as String, courseId: it.courseId as String]
            }
        }
        SignUpLog log = new SignUpLog(
                user: user,
                dateCreated: new Date(),
                flags: cmd.flags,
                type: 1
        )
        log.save()
        // 保存电话号码和Email
        def postInfo = PostInfo.findByUser(user)
        if (postInfo) {
            postInfo.phone = cmd.phone
            postInfo.email = cmd.email
            postInfo.save()
        } else {
            postInfo = new PostInfo(
                    address: '家庭住址',
                    seal: false,
                    phone: cmd.phone,
                    email: cmd.email,
                    user: user
            )
            if (!postInfo.save()) {
                postInfo.errors.each {
                    println it
                }
            }
        }
        return 'OK'
    }

    def other(String openId) {
        def user = User.findByOpenId(openId)
        if (user) {
            MakeUpEto.executeQuery'''
select new map(
    xn as xn,
    xq as xq,
    studentId as studentId,
    courseId as courseId,
    courseName as courseName,
    credit as credit,
    property as property,
    departmentName as departmentName,
    flag as flag,
    makeUpTime as makeUpTime
) from MakeUpEto
where xn='2019-2020' and xq = '2' and studentId = :userId and flag != '0'
order by flag
''', [userId: user.id]
        } else {
            return [:]
        }
    }

}
