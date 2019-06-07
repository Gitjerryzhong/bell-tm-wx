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
            <div id="t${term.xn}${term.xq}" class="level-exam">
                <g:findAll in="${scores}" expr="it.xn == term.xn && it.xq == term.xq">
                    <label>考试名称</label>
                    <h6>${it.examName}（${it.examId}）${it.dateOfExam}</h6>
                    <label>总成绩</label>
                    <h6>${it.score}</h6>
                    <g:if test="${it.scoreListening}">
                        <label>听力成绩</label>
                        <h6>${it.scoreListening}</h6>
                    </g:if>
                    <g:if test="${it.scoreReading}">
                        <label>阅读成绩</label>
                        <h6>${it.scoreReading}</h6>
                    </g:if>
                    <g:if test="${it.scoreWriting}">
                        <label>写作成绩</label>
                        <h6>${it.scoreWriting}</h6>
                    </g:if>
                    <g:if test="${it.scoreSpeaking}">
                        <label>口语成绩</label>
                        <h6>${it.scoreSpeaking}</h6>
                    </g:if>
                    <g:if test="${it.scoreComprehensive}">
                        <label>综合成绩</label>
                        <h6>${it.scoreComprehensive}</h6>
                    </g:if>
                    <g:if test="${it.examSpeakingId}">
                        <label>口语准考证</label>
                        <h6>${it.examSpeakingId}</h6>
                    </g:if>
                    <g:if test="${it.certificateId}">
                        <label>证书编号</label>
                        <h6>${it.certificateId}</h6>
                    </g:if>
                    <hr>
                </g:findAll>
            </div>
        </g:each>
    </div>
    <asset:javascript src="level-exam.js"/>
</body>
</html>
