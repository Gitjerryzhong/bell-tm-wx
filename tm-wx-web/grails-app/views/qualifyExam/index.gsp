<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>微信公众号</title>
    <asset:stylesheet src="weui.css"/>
    <asset:stylesheet src="wechat.css"/>
</head>
<body>
<g:if test="${openid}">
    <div class="weui-cell">
        <div class="weui-cell__hd"><label  class="weui-label">考生号</label></div>
            <input class="weui-input" id="examId" type="number" placeholder="请输入考生号"/>
        <div class="weui-cell__bd">
        </div>
    </div>
    <div class="weui-cell">
        <div class="weui-cell__hd"><label  class="weui-label">密码</label></div>
        <div class="weui-cell__bd">
            <input class="weui-input" id="ups" type="password" placeholder="请输入身份证后6位"/>
            <input type="hidden" id="openId" value="${code}">
        </div>
    </div>
    <div class="weui-btn-area">
        <input class="weui-btn weui-btn_primary" type="button" value="提交" id="confirm">
    </div>
    <div id="otherView"></div>
</g:if>
    <asset:javascript src="application.js"/>
    <asset:javascript src="qualify-exam.js"/>
</body>
</html>
