package cn.edu.bnuz.bell.wx

import cn.edu.bnuz.bell.cmd.UserCommand
import cn.edu.bnuz.bell.wx.dv.DvUser
import cn.edu.bnuz.bell.wx.eto.StudentEto
import grails.gorm.transactions.Transactional
import javassist.tools.web.BadHttpRequest

@Transactional
class BindUserService {

    static final Map Hint = [1: [label: '教师工号', placeholder: '请输入教师工号', root: 'teacher-menu'],
                             2: [label: '学号', placeholder: '请输入学号', root: 'student-menu']]
    def auth(UserCommand cmd) {
        if (DvUser.findByIdAndPassword(cmd.studentId, cmd.password)) {
            return true
        } else {
            return StudentEto.findByIdAndPassword(cmd.studentId, cmd.password ) ? true : false
        }

    }

    def bind(UserCommand cmd) {
        if (auth(cmd)) {
            if (!User.findByOpenId(cmd.openId) && !User.get(cmd.studentId)) {
                User user = new User(openId: cmd.openId, phone: cmd.phone, dateCreated: new Date())
                user.id = cmd.studentId
                if(!user.save()) {
                    user.errors.each {
                        println it
                    }
                }
                return 'OK'
            } else {
                return 'DUPLICATE'
            }
        } else {
            return 'FAIL'
        }
    }

    def checkOpenId(String id) {
        return User.findByOpenId(id) != null
    }

    def bindNewPhone(UserCommand cmd) {
        def user = User.findByOpenId(cmd.openId)
        if (!user) {
            throw new BadHttpRequest()
        }
        if (DvUser.findByIdAndPassword(user.id, cmd.password)) {
            def phoneUsed = user.phoneUsed ?: ''
            user.phoneUsed = phoneUsed + user.phone + ";"
            user.phone = cmd.phone
            user.save()
            return 'OK'
        } else {
            return 'FAIL'
        }

    }
}
