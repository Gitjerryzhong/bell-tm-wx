package cn.edu.bnuz.bell.wx

import cn.edu.bnuz.bell.wx.dv.Asset
import grails.gorm.transactions.Transactional

@Transactional
class AssetViewerService {

    def getAssetInfo(Long id) {
        Asset.load(id)
    }
}
