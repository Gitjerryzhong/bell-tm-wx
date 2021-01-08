<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>微信公众号</title>
    <meta name="layout" content="header">
    <asset:stylesheet src="weui.css"/>
    <asset:stylesheet src="wechat.css"/>
</head>
<body>
    <div class="weui-cells">
        <div class="weui-cell">
            <div class="weui-cell__hd"><label class="weui-label">名称</label></div>
            <div class="weui-cell__bd">
                ${asset.name}
            </div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><label  class="weui-label">类别</label></div>
            <div class="weui-cell__bd">
                ${asset.assetType}
            </div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><label  class="weui-label">资产编号</label></div>
            <div class="weui-cell__bd">
                ${asset.code}
            </div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><label  class="weui-label">品牌</label></div>
            <div class="weui-cell__bd">
                ${asset.brand}
            </div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><label  class="weui-label">型号规格</label></div>
            <div class="weui-cell__bd">
                ${asset.specs}
            </div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><label  class="weui-label">参数</label></div>
            <div class="weui-cell__bd">
                ${asset.parameter}
            </div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><label  class="weui-label">SN编号</label></div>
            <div class="weui-cell__bd">
                ${asset.sn}
            </div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><label  class="weui-label">存放地点</label></div>
            <div class="weui-cell__bd">
                ${asset.building} ${asset.place}
            </div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><label  class="weui-label">数量</label></div>
            <div class="weui-cell__bd">
                ${asset.pcs} ${asset.unit}
            </div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><label  class="weui-label">购买时间</label></div>
            <div class="weui-cell__bd">
                ${asset.dateBought}
            </div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><label  class="weui-label">状态</label></div>
            <div class="weui-cell__bd">
                ${asset.state}
            </div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><label  class="weui-label">保质期</label></div>
            <div class="weui-cell__bd">
                ${asset.qualifyMonth}个月
            </div>
        </div>
        <g:if test="${asset.dateForbid}">
            <div class="weui-cell">
            <div class="weui-cell__hd"><label  class="weui-label">报废日期</label></div>
            <div class="weui-cell__bd">
            ${asset.qualifyMonth}
            </div>
        </g:if>
        <g:if test="${asset.dateClose}">
            <div class="weui-cell">
            <div class="weui-cell__hd"><label  class="weui-label">核销日期</label></div>
            <div class="weui-cell__bd">
            ${asset.dateClose}
            </div>
        </g:if>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><label  class="weui-label">备注</label></div>
            <div class="weui-cell__bd">
                ${asset.note}
            </div>
        </div>
    </div>
    <g:if test="${tracks && tracks.length > 0}">
        <div class="weui_cells_title">资产流转足迹</div>
        <div class="weui-cells">
        <g:each in="${tracks}" var="item">
            <div class="weui-cell">
                <div class="weui-cell__hd"><label class="weui-label">${item.dateApproved}</label></div>
                <div class="weui-cell__bd">
                    ${item.operator} 从${item.source} ${item.type}(原状态：${item.state}
                </div>
            </div>
        </g:each>
        </div>
    </g:if>
    <g:if test="${changeLog}">
        <div class="weui_cells_title">变更日志</div>
        <div class="weui-cells">
            <g:each in="${changeLog}" var="log">
                <div class="weui-cell">
                    <div class="weui-cell__hd"><label class="weui-label">${log.dateCreated}</label></div>
                    <div class="weui-cell__bd">
                        原品牌：${log.brand}<br>
                        原型号：${log.specs}<br>
                        原参数：${log.parameter}<br>
                        原供应商：${log.supplier}<br>
                        变更原因：${log.sake}
                    </div>
                </div>
            </g:each>
        </div>
    </g:if>
</body>
</html>
