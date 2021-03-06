package cn.edu.bnuz.bell.wx

import cn.edu.bnuz.bell.cmd.SeatCommand
import cn.edu.bnuz.bell.wx.dv.DvUser
import cn.edu.bnuz.bell.wx.eto.CetArrange
import grails.gorm.transactions.Transactional
import javassist.tools.web.BadHttpRequest

@Transactional
class TakeSeatService {

    def save(String openId,  SeatCommand cmd) {
        def user = CetTeacher.findByOpenId(openId)
        if (user) {
            // 禁止当次不排考的老师扫码占座
//            def cet_room = CetTeacherRoom.findByCetTeacher(user)
//            if (!cet_room) {
//                return '您不是本次监考，请在无二维码标签处就坐。'
//            }
            // 禁止多占座
            def activity = Activities.load(cmd.activityId)
            def s = Seat.findByUserAndActivity(user, activity)
            if (s) {
                def seatLabel = getSeatLabel(s.row, s.col, activity.groups)
                return "${user.name} 老师，您已经占座，请到${seatLabel}就坐"
            }

            // 防止多人抢座，列为999的时候，不捡测座位
            if (cmd.col != 999 && Seat.findByActivityAndColAndRow(activity, cmd.col, cmd.row)) {
                return '该位置已经有人，请扫其他座位'
            }
            Seat seat = new Seat(
                    user: user,
                    activity: activity,
                    row: cmd.row,
                    col: cmd.col,
                    dateCreated: new Date()
            )
            seat.save()
            return "${user.name} 老师，您的座位是：${getSeatLabel(seat.row, seat.col, activity.groups)}"
        } else {
            return 'FAIL'
        }
    }

    def getExamInfo(String openId) {
        def user = CetTeacher.findByOpenId(openId)
        if (user) {
            user.checked = true
            user.save()
            def seat = Seat.executeQuery'''
select new map(
    seat.row as row,
    seat.col as col,
    seat.dateCreated as dateCreated,
    a.name as activity,
    a.groups as groups
)
from Seat seat
join seat.activity a
join seat.user u
where u.openId = :openId
order by seat.dateCreated desc
''', [openId: openId], [max: 1]

            // 查询所监考考场
            def cetTeacherRooms = CetArrange.executeQuery'''
select new map(
    ca.teacher1 as teacher1,
    ca.phone1 as phone1,
    ca.teacher2 as teacher2,
    ca.phone2 as phone2,
    ca.examName as examName,
    ca.examPlace as roomPlace,
    ca.groupPlace as  groupPlace,
    ca.captainPhone as captainPhone,
    ca.roomName as roomName,
    e.paperTime as paperTime,
    e.examTime as examTime
)
from CetArrange ca, Exam e
where ca.examName = e.name and (ca.phone1 = :phone or ca.phone2 = :phone)
''', [phone: user.phone]
//            def cetTeacherRooms = CetTeacherRoom.executeQuery'''
//select new map(
//   ct.name as teacherName,
//   e.name as examName,
//   er.name as roomName,
//   er.id as roomId,
//   er.place as roomPlace,
//   e.examTime as examTime,
//   e.paperTime as paperTime,
//   g.place as groupPlace,
//   c.phone as captainPhone
//)
//from CetTeacherRoom ctr
//join ctr.cetTeacher ct
//join ctr.examRoom er
//join er.exam e
//join er.group g
//join g.captain c
//where ct.id = :id
//''', [id: user.id]
            cetTeacherRooms.each { entry ->
//                def other = CetTeacherRoom.findByExamRoomAndCetTeacherNotEqual(ExamRoom.load(entry.roomId), user).cetTeacher
                entry['otherTeacherName'] = entry.phone1 == user.phone ? entry.teacher2 : entry.teacher1
                entry['otherTeacherPhone'] = entry.phone1 == user.phone ? entry.phone2 : entry.phone1
                entry['teacherName'] = entry.phone1 == user.phone ? entry.teacher1 : entry.teacher2
            }
            return [seat: seat ? getSeatLabel(seat[0].row, seat[0].col, seat[0].groups) : null,
                    cetTeacherRooms: cetTeacherRooms,
                    trainingConfig: TrainingConfig.load(1)
            ]
        }
        return null
    }

    static getSeatLabel(Integer row, Integer col, String groupsString = null) {
        if (col == 999) {
            return '随机座位'
        }
        def label = "${row}排${col}列"
        if (groupsString) {
            def groups = groupsString.split(',')
            def c = col
            Character i = 'A'
            for (g in groups) {
                if (c <= Integer.valueOf(g)) {
                    break
                }
                c -= Integer.valueOf(g)
                i++
            }
            label = "${i}区${row}排${c}列"
        }
        return label
    }
}
