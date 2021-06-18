package cn.edu.bnuz.bell.wx.eto

class CetArrange {
    String captainPhone
    String groupPlace
    String roomName
    String examName
    String examPlace
    String teacher1
    String department1
    String phone1
    String teacher2
    String department2
    String phone2

    static mapping = {
        table       name: 'et_cet_arrange', schema: 'tm_wx'
    }
}
