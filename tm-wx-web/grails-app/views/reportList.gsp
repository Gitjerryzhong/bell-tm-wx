<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>申请结果汇总</title>
    <meta name="layout" content="header">
    <asset:stylesheet src="weui.css"/>
    <asset:stylesheet src="wechat.css"/>
</head>
<body>
    <h2>申请结果汇总（不包含今天申请）</h2>
<div class="weui-cell  weui-cell_swiped">
    <div class="weui-cells" id="resultView">
        <g:each in="${list}" var="item" status="i">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <p>${i + 1}. ${item.userId} ${item.name} ${item.phone} <br>${item.seal ? '需密封' : '不密封'}, ${item.sealComment}
                        <br>地址：${item.address}</p>
                </div>
                <div class="weui-cell__ft">
                    <ul>
                        <g:each in="${item.reports}" var="report">
                            <li>${report.type}<span class="weui-badge" style="margin-left: 5px;">${report.ps}</span></li>
                        </g:each>
                    </ul>
                </div>
            </div>
        </g:each>
    </div>
</div>
</body>
</html>
