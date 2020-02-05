<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>讨论</title>
%{--    <meta name="layout" content="header">--}%
    <asset:stylesheet src="weui.css"/>
    <asset:stylesheet src="wechat.css"/>
    <asset:stylesheet src="wx.css"/>
    <asset:stylesheet src="talking.css"/>
</head>
<body>
<div class="weui-search-bar">
    <input type="search" class="search-input" id="search" placeholder="关键字"><button class="weui-btn weui-btn_mini weui-btn_primary" id="searchBtn">搜索</button>
    <input type="hidden" id="openId" value="${openid}">
    <input type="hidden" id="themeId" value="${themeId}">
</div>
<g:if test="talking.permission.viewAble">
    <div class="weui-news" id="view">
    <g:each in="${talking.list}" var="item">
        <div class="weui-news-inners">
            <div class="weui-news-info">
                <div class="weui-news-infoitem">
                    <span>${item.name}</span>
                </div>
                <div class="weui-news-infoitem">${item.dateCreated}</div>
            </div>
            <div class="weui-news-text">
                <p class="weui-news-p">${item.content}</p>
            </div>
        </div>
    </g:each>
    </div>
</g:if>
<g:if test="talking.permission.speakAble">
    <div class="footer">
        <g:textArea name="content" id="content" rows="4"/>
        <button class="weui-btn weui-btn_mini weui-btn_primary" id="ok">确定</button>
    </div>
</g:if>
<asset:javascript src="application.js"/>
<asset:javascript src="talking.js"/>
</body>
</html>
