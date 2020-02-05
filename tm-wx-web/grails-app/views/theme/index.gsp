<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>主题</title>
    <meta name="layout" content="header">
    <asset:stylesheet src="weui.css"/>
    <asset:stylesheet src="wechat.css"/>
    <asset:stylesheet src="wx.css"/>
</head>
<body>
    <div class="weui-btn-area">
        <ul>
            <g:each in="${list}" var="item">
                <li><a href="/newFire?act=talking&themeId=${item.id}">${item.title} (${item.attendAble ? '可参与讨论' : '不可参与讨论'})</a></li>
            </g:each>
        </ul>

    </div>
</body>
</html>
