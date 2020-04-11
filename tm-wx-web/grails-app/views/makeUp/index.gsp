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
<g:if test="${user.grade <= 2016}">
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
        <input type="hidden" id="list" value="${list}">
        <input type="hidden" id="validPhone" value="${user.phone2 ? user.phone2 : user.phone1}">
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

    <div class="weui-form__control-area">
        <div class="weui-panel weui-panel_access">
            <div class="weui-panel__hd">需确认补考科目</div>
            <div class="weui-panel__bd">
            <g:each in="${list}" status="i" var="item">
                <div class="weui-media-box weui-media-box_text">
                    <div class="weui-cell weui-cell_active weui-cell_select weui-cell_select-after">
                        <div class="weui-cell__hd">
                            <label class="weui-label">${item.courseName}</label>
                        </div>
                        <div class="weui-cell__bd">
                            <select class="weui-select" name="flag${i}" id="flag${i}">
                                <option value="0,${item.courseId}">未确认</option>
                                <option value="1,${item.courseId}">参加补考</option>
                                <option value="-1,${item.courseId}">不参加补考</option>
                            </select>
                        </div>
                    </div>
                    <p class="weui-media-box__desc">${item.departmentName} ${item.property} ${item.credit}学分</p>
                </div>
            </g:each>
            </div>
        </div>
    </div>

    <div class="weui-btn-area">
        <input class="weui-btn weui-btn_primary" type="button" value="提交" id="confirm">
    </div>
    <div id="otherView"></div>
</g:if>
<g:else>
    <div class="warning_message">本次线上考试，只针对2016级和往届生，其他年级同学待返校后学校另行组织。</div>
</g:else>
    <asset:javascript src="application.js"/>
    <asset:javascript src="makeup.js"/>
</body>
</html>
