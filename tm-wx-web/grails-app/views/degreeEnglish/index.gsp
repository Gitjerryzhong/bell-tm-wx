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
<g:if test="${user != null && user.grade <= 2016}">
    <div class="weui-cell">
        <div class="weui-cell__hd"><labeuserIdl  class="weui-label">学号</labeuserIdl></div>
        <div class="weui-cell__bd">
            ${user.userId}
        </div>
    </div>

    <div class="weui-cell">
        <div class="weui-cell__hd"><label  class="weui-label">姓名</label></div>
        <div class="weui-cell__bd">
            ${user.name}
        </div>
        <input type="hidden" id="openId" value="${user.openId}">
        <input type="hidden" id="sms" value="${sms}">
        <input type="hidden" id="validPhone" value="${user.phone2 ? user.phone2 : user.phone1}">
    </div>
    <div class="weui-cell">
        <div class="weui-cell__hd"><labeuserIdl  class="weui-label">学院</labeuserIdl></div>
        <div class="weui-cell__bd">
            ${user.department}
        </div>
    </div>
    <div class="weui-cell">
        <div class="weui-cell__hd"><labeuserIdl  class="weui-label">专业</labeuserIdl></div>
        <div class="weui-cell__bd">
            ${user.major}
        </div>
    </div>
    <div class="weui-cell">
        <div class="weui-cell__hd"><labeuserIdl  class="weui-label">年级</labeuserIdl></div>
        <div class="weui-cell__bd">
            ${user.grade}
        </div>
    </div>

    <div class="weui-cell weui-cell_vcode">
        <div class="weui-cell__hd"><label  class="weui-label">联系电话</label></div>
        <div class="weui-cell__bd">
            ${user.phone2 ? user.phone2 : user.phone1}
        </div>
        <div class="weui-cell__ft">
            <button class="weui-vcode-btn" id="changePhone">更换</button>
        </div>
    </div>
    <div id="newPhone"></div>
    <div class="weui-cell weui-cell_active weui-cell_vcode">
        <div class="weui-cell__hd">
            <label class="weui-label">Email</label>
        </div>
        <div class="weui-cell__bd">
            <input class="weui-input" type="email" value="${user.email}" placeholder="请输入Email" id="email"/>
        </div>
    </div>
    <div class="weui-cells__title">需要确认的学位外语考试类别</div>
    <div class="weui-cell weui-cell_active weui-cell_select weui-cell_select-after">
        <div class="weui-cell__hd">
            <label class="weui-label">${user.examType}</label>
        </div>
        <div class="weui-cell__bd">
            <g:if test="${user.flag != '0'}">
                ${user.flag == '1' ? "参加补考" : "不参加补考"}
            </g:if>
            <g:else>
                <select class="weui-select" name="flag" id="flag">
                    <option value="0">未确认</option>
                    <option value="1">参加补考</option>
                    <option value="-1">不参加补考</option>
                </select>
            </g:else>
        </div>
    </div>

    <div class="weui-btn-area">
        <input class="weui-btn weui-btn_primary" type="button" value="提交" id="confirm">
    </div>
</g:if>
<g:else>
    <div class="warning_message">本次学位外语线上考试，只针对2016级和往届生，其他年级同学待返校后学校另行组织。</div>
</g:else>
    <asset:javascript src="application.js"/>
    <asset:javascript src="degree-english.js"/>
</body>
</html>
