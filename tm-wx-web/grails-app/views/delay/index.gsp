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
        <input type="hidden" id="openId" value="${openid}">
        <input type="hidden" id="sealValue" value="${user.seal}">
        <input type="hidden" id="sealCommentValue" value="${user.sealComment}">
        <input type="hidden" id="addressValue" value="${user.address}">
    </div>

    <div class="weui-form__control-area">
        <div class="weui-cells__group weui-cells__group_form">
            <div class="weui-cell weui-cell_active weui-cell_select weui-cell_select-after">
                <div class="weui-cell__hd">
                    <label for="type" class="weui-label">文件类型</label>
                </div>
                <div class="weui-cell__bd">
                    <div id="typeView"></div>
                </div>

            </div>
            <div class="weui-cell weui-cell_active weui-cell_vcode">
                <div class="weui-cell__hd">
                    <label class="weui-label">打印份数</label>
                </div>
                <div class="weui-cell__bd">
                    <input class="weui-input" type="number" placeholder="请输入打印份数" id="ps"/>
                </div>
            </div>
            <div class="weui-cell weui-cell_active weui-cell_vcode">
                <div class="weui-cell__hd">
                    <label class="weui-label">手机号</label>
                </div>
                <div class="weui-cell__bd">
                    <input class="weui-input" type="tel" placeholder="请输入手机号" value="${user.phone2 ? user.phone2 : user.phone1}" id="phone"/>
                </div>
            </div>
            <div class="weui-cell weui-cell_active weui-cell_switch">
                <div class="weui-cell__bd">是否密封</div>
                <div class="weui-cell__ft">
                    <input class="weui-switch" type="checkbox" id="seal"/>
                </div>
            </div>
            <div class="weui-cells__title">密封描述</div>
            <div class="weui-cells weui-cells_form">
                <div class="weui-cell ">
                    <div class="weui-cell__bd">
                        <textarea class="weui-textarea" placeholder="请描述密封要求" rows="2" id="sealComment" value="${user.sealComment}"></textarea>
                        <div class="weui-textarea-counter"><span>0</span>/200</div>
                    </div>
                </div>
            </div>
            <div class="weui-cells__title">邮寄地址</div>
            <div class="weui-cells weui-cells_form">
                <div class="weui-cell ">
                    <div class="weui-cell__bd">
                        <textarea class="weui-textarea"  placeholder="请填写邮寄地址" rows="2" id="address" value="${user.address}"></textarea>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="weui-btn-area">
        <input class="weui-btn weui-btn_primary" type="button" value="提交" id="ok">
    </div>

    <div class="weui-cells__title">今天申请结果汇总</div>

    <div class="weui-cell  weui-cell_swiped">
        <div class="weui-cells" id="resultView">
        <g:each in="${list}" var="item">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <p>${item.type}<span class="weui-badge" style="margin-left: 5px;">${item.ps}</span></p>
                </div>
            </div>
            <g:if test="${item.removeAble}">
            <div class="weui-cell__ft">
                <button class="weui-btn weui-btn_mini weui-btn_warn"
                        style="margin-right: 10px; margin-bottom: 10px"
                        onclick="remove(${item.id})">删除</button>
            </div>
            </g:if>
        </g:each>
        </div>
    </div>

    <asset:javascript src="application.js"/>
    <asset:javascript src="delay.js"/>
</body>
</html>
