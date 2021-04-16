package cn.edu.bnuz.bell.wx

import cn.edu.bnuz.bell.wx.dv.AssetCart
import cn.edu.bnuz.bell.wx.dv.DvAsset
import cn.edu.bnuz.bell.wx.dv.DvAssetChangeLog

import cn.edu.bnuz.bell.wx.dv.DvAssetTrack
import cn.edu.bnuz.bell.wx.dv.DvAssetUser
import grails.gorm.transactions.Transactional
import javassist.tools.web.BadHttpRequest

@Transactional
class AssetViewerService {

    def getAssetInfo(Long id, String openid) {
        DvAsset asset = DvAsset.get(id)
        if (asset) {
            def user = User.findByOpenId(openid)
            if (user?.id?.length() != 5) {
                throw new BadHttpRequest()
            }
            if (!AssetCart.findByAssetIdAndNameIsNull(id)) {
                AssetCart cart = new AssetCart(
                        assetId: asset.id,
                        userId: user.id
                )
                if (!cart.save()) {
                    cart.errors.each {
                        println it
                    }
                }
            }
        }
        return asset
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

    def getChangeLog(Long id) {
        DvAssetChangeLog.executeQuery("from DvAssetChangeLog where assetId = :id", [id: id])
    }
}
