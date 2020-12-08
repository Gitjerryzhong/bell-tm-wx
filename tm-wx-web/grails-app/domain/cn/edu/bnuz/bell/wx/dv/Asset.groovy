package cn.edu.bnuz.bell.wx.dv

import java.time.LocalDate

class Asset {
    Long id
    String code
    String sn
    String name
    String assetType
    BigDecimal price
    String state
    LocalDate dateBought
    LocalDate dateForbid
    LocalDate dateClose
    Integer pcs
    String unit
    Integer qualifyMonth
    String note
    String place
    String building
    String brand
    String specs
    String parameter
    String supplier
    static mapping = {
        table schema: 'tm_wx'
    }

}
