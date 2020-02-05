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
    <g:if test="${key != null}">
        ${key}
    </g:if>
    <g:else>
        <div class="weui-cell">
            <div class="weui-cell__hd"><label id="uLabel" class="weui-label">昵称</label></div>
            <div class="weui-cell__bd">
                <input class="weui-input" id="uid" type="text"/>
            </div>
        </div>

        <div class="weui-cell">
            <div class="weui-cell__hd"><label  class="weui-label">验证码</label></div>
            <div class="weui-cell__bd">
                <input class="weui-input" id="ups" type="text" placeholder="验证码"/>
                <input type="hidden" id="openId" value="${openid}">
            </div>
        </div>


        <div class="weui-btn-area">
            <input class="weui-btn weui-btn_primary" type="button" value="注册" onclick="register()">
        </div>
        <asset:javascript src="application.js"/>
        <asset:javascript src="register.js"/>
    </g:else>
</body>
</html>
