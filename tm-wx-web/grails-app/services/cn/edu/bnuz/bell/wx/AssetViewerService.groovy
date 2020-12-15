package cn.edu.bnuz.bell.wx

import cn.edu.bnuz.bell.wx.dv.DvAsset
import cn.edu.bnuz.bell.wx.dv.DvAssetTrack
import cn.edu.bnuz.bell.wx.dv.DvAssetUser
import grails.gorm.transactions.Transactional

@Transactional
class AssetViewerService {

    def getAssetInfo(Long id) {
        DvAsset.executeQuery("from DvAsset where id = :id", [id: id])
    }

    Boolean hasPermission(String openId) {
        def result = DvAssetUser.executeQuery'''
select au.roleId
from DvAssetUser au, User u
where au.userId = u.id and u.openId = :openId
''', [openId: openId]
        return result != null
    }

    def getTrack(Long id) {
        DvAssetTrack.executeQuery'''
select new map(
id as id,
type as type,
operator as operator,
state as state,
dateApproved as dateApproved,
source as source,
note as note
)
from DvAssetTrack
where id = :id
''', [id: id]
    }
}
