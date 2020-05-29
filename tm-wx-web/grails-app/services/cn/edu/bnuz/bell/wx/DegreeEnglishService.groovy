package cn.edu.bnuz.bell.wx

import cn.edu.bnuz.bell.cmd.DegreeEnglishCommand
import grails.gorm.transactions.Transactional

@Transactional
class DegreeEnglishService {

    def getUserInfo(String openId) {
        def result = PostInfo.executeQuery'''
select new map(
u.openId as openId,
u.id as userId,
se.name as name,
se.department as department,
se.grade as grade,
se.major as major,
ee.type as examType,
ee.flag as flag,
ee.dateExam as dateExam,
ee.teacherName as teacherName,
ee.teacherPhone as teacherPhone,
u.phone as phone1,
i.phone as phone2,
i.email as email
)
from PostInfo i
right join i.user u,
StudentEto se,
EnglishExam ee
where u.openId = :openId and se.id = u.id and ee.studentId = u.id
''', [openId: openId]
        return result ? result[0] : null
    }

    def update(DegreeEnglishCommand cmd) {
        def user = User.findByOpenId(cmd.openId)
        if (!user) {
            return 'FAIL'
        }
        def degreeEnglish = EnglishExam.findByStudentId(user.id)
        if (!degreeEnglish) {
            return 'FAIL'
        }
        def activity = Activities.findByName('DEGREE ENGLISH')
        def now = new Date()
        if(activity.start > now || activity.deadline < now) {
            return 'EXPIRE'
        }
        if (degreeEnglish.flag == '0') {
            degreeEnglish.flag = cmd.flag
            degreeEnglish.save()
            SignUpLog log = new SignUpLog(
                    user: user,
                    dateCreated: new Date(),
                    flags: cmd.flag,
                    type: 2
            )
            log.save()
        }

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
}
