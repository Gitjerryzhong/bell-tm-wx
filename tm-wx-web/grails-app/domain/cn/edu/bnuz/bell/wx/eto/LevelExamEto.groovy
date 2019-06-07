package cn.edu.bnuz.bell.wx.eto

class LevelExamEto {
    String xn
    Integer xq
    String studentId
    String studentName
    String examName
    String dateOfExam
    String score
    String examId
    String scoreListening
    String scoreReading
    String scoreWriting
    String scoreComprehensive
    String scoreSpeaking
    String examSpeakingId
    String certificateId

    static mapping = {
        table       name: 'et_level_exam', schema: 'tm_wx'
    }
}
