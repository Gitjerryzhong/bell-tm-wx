package cn.edu.bnuz.bell.wx

import cn.edu.bnuz.bell.cmd.UserCommand
import cn.edu.bnuz.bell.wx.dv.DvUser
import grails.gorm.transactions.Transactional
import javassist.tools.web.BadHttpRequest

@Transactional
class BindCetTeacherService {

    def bind(UserCommand cmd) {
        if (cmd.phone) {
            CetTeacher teacher = CetTeacher.findByPhone(cmd.phone)
            if (teacher) {
                // 如果是TM用户
                def tmUser = DvUser.findById(teacher.teacherId)
                if (tmUser) {
                    if (!User.findByOpenId(cmd.openId) && !User.get(teacher.teacherId)) {
                        User user = new User(openId: cmd.openId, phone: cmd.phone, dateCreated: new Date())
                        user.id = teacher.teacherId
                        user.save()
                    } else {
                        return 'DUPLICATE'
                    }
                }
                teacher.openId = cmd.openId
                teacher.save()
                return 'OK'
            } else {
                return 'FAIL'
            }
        }
    }

    // 检查是否已作为TM用户绑定过，在检查是否在CET老师中绑定
    def checkOpenId(String id) {
        def user = User.findByOpenId(id)
        if (user) {
            CetTeacher teacher = CetTeacher.findByTeacherId(user.id)
            if (teacher) {
                if (!teacher.openId) {
                    teacher.setOpenId(user.openId)
                    teacher.save()
                }
                return '您已绑定，无需重复操作！'
            } else {
                return '您未报名四六级监考！'
            }
        } else {
            def teacher = CetTeacher.findByOpenId(id)
            if (teacher) {
                return '您已绑定，无需重复操作！'
            }
            return 'PASS'
        }
    }

    def checkPhone(UserCommand cmd) {
        if (!cmd.phone) {
            throw new BadHttpRequest()
        }
        return CetTeacher.findByPhone(cmd.phone) != null
    }
}
