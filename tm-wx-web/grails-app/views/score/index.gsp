<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>成绩</title>
    <meta name="layout" content="header">
    <asset:stylesheet src="weui.css"/>
    <asset:stylesheet src="wechat.css"/>
    <asset:stylesheet src="wx.css"/>
</head>
<body>
    <div class="weui-btn-area">
        <g:each in="${terms}" var="term">
            <input class="weui-btn weui-btn_plain-default" type="button" value="${term.xn}学年 第${term.xq}学期" onclick="showOrHide('${term.xn}', '${term.xq}')">
            <table class="table table-expand" id="t${term.xn}${term.xq}">
                <thead>
                <tr>
                    <th>课程</th>
                    <th>学分</th>
                    <th>分数</th>
                </tr>
                </thead>
                <tbody>
                <g:findAll in="${scores}" expr="it.xn == term.xn && it.xq == term.xq">
                    <tr>
                        <td>${it.courseName}</td>
                        <td>${it.credit}</td>
                        <td><g:if test="${it.scoreNumber < 60}"><span class="weui-badge">${it.score}</span></g:if>
                            <g:else>
                                ${it.score}
                            </g:else>
                        </td>
                    </tr>
                </g:findAll>
                </tbody>
            </table>
        </g:each>
    </div>
    <asset:javascript src="score.js"/>
</body>
</html>
