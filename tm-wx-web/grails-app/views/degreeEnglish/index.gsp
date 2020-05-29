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
    <g:if test="${user.dateExam != null}">
    <div class="important_message">
        <div class="weui-cell">
            <div class="weui-cell__hd"><labeuserIdl  class="weui-label">考试时间</labeuserIdl></div>
            <div class="weui-cell__bd">
                ${user.dateExam}
            </div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><labeuserIdl  class="weui-label">监考老师</labeuserIdl></div>
            <div class="weui-cell__bd">
                ${user.teacherName.substring(0,1)}老师
            </div>
        </div>
        <div class="weui-cell">
            <div class="weui-cell__hd"><labeuserIdl  class="weui-label">电话</labeuserIdl></div>
            <div class="weui-cell__bd">
                ${user.teacherPhone}
            </div>
        </div>
        <p class="warning" style="padding: 5px;">温馨提醒：请各位考生关注监考教师的个人信息，谨防有人冒名顶替，骗取隐私、财产等。</p>
    </div>
    </g:if>
    <div class="weui-cell">
        <div class="weui-cell__hd"><label  class="weui-label">联系电话</label></div>
        <div class="weui-cell__bd">
            ${user.phone2 ? user.phone2 : user.phone1}
        </div>
        <g:if test="${user.flag == '0'}">
        <div class="weui-cell__ft">
            <button class="weui-vcode-btn" id="changePhone">更换</button>
        </div>
        </g:if>
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
    <g:if test="${user.flag == '0'}">
    <div class="weui-cell weui-cell_active weui-cell_select weui-cell_select-after">
        <div class="weui-cell__hd">
            <label class="weui-label">${user.examType}</label>
        </div>
        <div class="weui-cell__bd">
            <select class="weui-select" name="flag" id="flag">
                <option value="0">未确认</option>
                <option value="1">参加考试</option>
                <option value="-1">不参加考试</option>
            </select>
        </div>
    </div>

    <div class="weui-btn-area">
        <input class="weui-btn weui-btn_primary" type="button" value="提交" id="confirm">
    </div>
    </g:if>
    <g:else>
        <div class="weui-media-box weui-media-box_text">
        ${user.examType}：<strong>${user.flag == '1' ? "参加考试" : "不参加考试"}</strong>
        </div>
    </g:else>
</g:if>
<g:else>
    <div class="warning_message">本次学位外语考试仅面向2020届毕业生，具体报名资格请查看通知。如有疑问，请咨询侯老师0756-6126905。</div>
</g:else>
    <asset:javascript src="application.js"/>
    <asset:javascript src="degree-english.js"/>
</body>
</html>
