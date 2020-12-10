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
    <div class="page__bd">
    <article class="weui-article">
        <g:if test="cetTeacherRooms != null">
        <p>监考培训时间：请您于<strong>${trainingConfig.time1}</strong>前至<strong>${trainingConfig.place1}</strong>参加监考员培训会。</p>
        <p class="warning">本次培训会采用微信扫码签到，请务必携带手机参加。</p>
        <g:if test="${seat !=null}">
            <p>您的培训座位：<span class="warning">${seat}</span></p>
        </g:if>
        <hr style="margin-bottom: 2em;">
        <g:each in="${cetTeacherRooms}" var="cetTeacherRoom">
            <h1>全国大学${cetTeacherRoom.examName}考试监考聘书</h1>
            <p><strong>${cetTeacherRoom.teacherName}</strong>老师：</p>
            <p>兹由我校大学英语四、六级组委会决定，特聘请您担任我校本次大学${cetTeacherRoom.examName}考试第<strong>${cetTeacherRoom.roomName}</strong>考场监考员。
            监考地点：<strong>${cetTeacherRoom.roomPlace}</strong>。同考场另一监考员为：<strong>${cetTeacherRoom.otherTeacherName}</strong>老师，
            联系电话:<strong>${cetTeacherRoom.otherTeacherPhone}</strong></p>
            <p>考试时间：<strong>${cetTeacherRoom.examTime}</strong>(请于<strong>${cetTeacherRoom.paperTime}至${cetTeacherRoom.groupPlace}</strong>
                领取试卷,资料组联系电话：<strong>${cetTeacherRoom.captainPhone}</strong></p>
            <hr style="margin-bottom: 2em;">
        </g:each>
        <h2>注意：</h2>
        <p>请您准时参加学校举行的培训会，了解最新的监考要求。</p>
        <p>因故无法参加下午培训会的，请于<span class="warning">${trainingConfig.time2}</span>到<span class="warning">${trainingConfig.place2}</span>参加第二场培训会。</p>
        <p>严禁监考人员在考点内违规携带或使用通讯、摄像等电子工具。</p>
        <p>根据财务处规定，本次CET监考费将发至下月工资卡中，请注意查收。如有疑问，请咨询教务处。</p>
        </g:if>
        <g:else>
            <div class="warning_message">您未参加此次四六级监考！</div>
        </g:else>
    </article>
    </div>
</body>
</html>
