package cn.edu.bnuz.bell

import cn.edu.bnuz.bell.wx.AssetViewerService
import cn.edu.bnuz.bell.wx.AuthService

class AssetViewerController {
    AuthService authService
    AssetViewerService assetViewerService

    def show(String code, Long id) {
        def openid = authService.findOpenId(code)
        if (!openid) {
            render view:"/message", model: [message: "非法用户！"]
        } else {
            if (assetViewerService.hasPermission(openid)) {
                def asset = assetViewerService.getAssetInfo(id, openid)
                if (!asset) {
                    render view:"/message", model: [message: "该设备不存在，请联系系统管理员！"]
                } else {
                    return [asset: asset,
                            tracks: assetViewerService.getTrack(id),
                            changeLog: assetViewerService.getChangeLog(id)
                    ]
                }
            }
        }
    }
}
