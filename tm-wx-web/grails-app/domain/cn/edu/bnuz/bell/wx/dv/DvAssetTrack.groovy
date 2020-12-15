package cn.edu.bnuz.bell.wx.dv

import java.time.LocalDate

class DvAssetTrack {
    Long id
    String type
    String operator
    String state
    LocalDate dateApproved
    String source
    String note
    static mapping = {
        table schema: 'tm_wx'
    }
}
