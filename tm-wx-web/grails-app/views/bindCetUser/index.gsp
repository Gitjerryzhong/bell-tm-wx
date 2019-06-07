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
    <div class="weui-cell">
        <div class="weui-cell__hd"><label  class="weui-label">手机</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" id="phone" type="tel" placeholder="请输入手机号码"/>
            <input type="hidden" id="openId" value="${openid}">
        </div>
        <div class="weui-cell__ft">
            <button class="weui-btn weui-btn_mini weui-btn_primary" id="getCode" onclick="getCode('${sms}')">获取验证码</button>
        </div>
    </div>
    <div class="weui-cell">
        <div class="weui-cell__hd"><label  class="weui-label">验证码</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" id="userCode" type="number" placeholder="请输入验证码"/>
        </div>
    </div>

    <div class="weui-btn-area">
        <input class="weui-btn weui-btn_primary" type="button" value="绑定" onclick="bindUser()">
    </div>
    <asset:javascript src="application.js"/>
    <asset:javascript src="bind-cet-user.js"/>
</body>
</html>
