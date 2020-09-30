package cn.edu.bnuz.bell.wx


import grails.gorm.transactions.Transactional

@Transactional
class QualifyExamService {

    def getExamInfo(String examId, String password) {
        def result = QualifyExamStudent.executeQuery'''
select new map(
student.name as name,
student.examId as examId,
student.type as type,
student.examPlace as examPlace,
student.examTime as examTime,
student.roomId as roomId,
room.place as roomName 
)
from QualifyExamStudent student, QualifyExamRoom room
where student.examTime = room.examTime
and student.roomId = room.roomNum
and student.type = room.type
and student.examId = :examId
and  substring(student.idNum, 13, 6) = :password
''', [examId: examId, password: password]
        return result ? result[0] : [:]
    }
}
