$('#confirm').on('click', function(event){
    var email = $("#email").val();
    var phone = $("#phone").val();
    var list = $("#list").val();
    console.log(list);
    // 匹配出有几门课
    var regex = /courseId=([^}.]*)/g;
    var ids;
    var validPhone = $("#validPhone").val();
    var id_counts =0;
    while((ids = regex.exec(list)) != null) {
        id_counts ++;
    }
    var flags = [];
    for (var i = 0 ; i < id_counts; i++) {
        var flagStr = $("#flag" + i).val();
        if (flagStr != undefined) {
            var flagValues = flagStr.split(",");
            if (flagValues[0] === '0') {
                alert('存在未确认考试');
                return;
            }
            flags.push({courseId: flagValues[1], flag: flagValues[0]});
        }
    }
    var userCode = $("#userCode").val();

    if (phone !== undefined) {
        if (!phone || phone.length !== 11) {
            alert('请正确输入手机号码！');
        } else if ( code !== undefined && code !== null &&  code == userCode){
            validPhone = phone;
        } else {
            alert("请正确输入验证码！");
        }
    }
    var re=/^\w+@[a-z0-9]+\.[a-z]+$/i;
    if ( !email ) {
        alert('请输入Email。');
    } else if (!re.test(email)) {
        alert('Email无效。');
    } else if ( !validPhone ) {
        alert('电话号码不允许空！请输入电话号码。');
    } else {
        $.post("/makeUp",
            {
                openId: $("#openId").val(),
                phone: validPhone,
                email: email,
                flags:   JSON.stringify(flags)
            },
            function(data, status) {
                if (status === 'success') {
                    if (data.state === 'OK') {
                        alert("报名成功！");
                        $(location).attr('href', '/student-menu');
                    } else {
                        alert("未知错误！");
                    }
                } else {
                    alert("网络错误！");
                }
            });
    }
});

$('#changePhone').on('click', function(event){
    var template = "<div class=\"weui-cell\">\n" +
        "        <div class=\"weui-cell__hd\"><label  class=\"weui-label\">新手机号</label></div>\n" +
        "        <div class=\"weui-cell__bd\">\n" +
        "            <input class=\"weui-input\" id=\"phone\" type=\"tel\" placeholder=\"请输入手机号码\"/>\n" +
        "        </div>\n" +
        "        <div class=\"weui-cell__ft\">\n" +
        "            <button class=\"weui-btn weui-btn_mini weui-btn_primary\" id=\"getCode\" onclick=\"onGetCode()\">获取验证码</button>\n" +
        "        </div>\n" +
        "    </div>\n" +
        "    <div class=\"weui-cell\">\n" +
        "        <div class=\"weui-cell__hd\"><label  class=\"weui-label\">验证码</label></div>\n" +
        "        <div class=\"weui-cell__bd\">\n" +
        "            <input class=\"weui-input\" id=\"userCode\" type=\"number\" placeholder=\"请输入验证码\"/>\n" +
        "        </div>\n" +
        "    </div>";
    $('#newPhone')[0].innerHTML = template;
});

function onGetCode() {
    getCode($("#sms").val())
}

setTimeout(function(){
    $.get("/makeUp/otherMakeUp",
        {
            openId: $("#openId").val(),
        },
        function(data, status) {
            if (status === 'success') {
                if (data != null && data.length > 0) {
                    var html = "<div class=\"weui-cells__title title\">其他补考项目</div> \n";
                    var template = "<div class=\"weui-media-box weui-media-box_text\">\n" +
                        "    <div>\n" +
                        "        ${item.courseName}: <strong>${item.makeUpTime}</strong>        \n" +
                        "    </div>\n" +
                        "    <p class=\"weui-media-box__desc\">${item.departmentName} ${item.property} ${item.credit}学分</p>\n" +
                        "</div>";
                    data.forEach(function (item) {
                        var tooltip = item.makeUpTime;
                        if (tooltip == null || tooltip === 'null') {
                            tooltip = item.flag == '1' ? "参加补考" : "不参加补考";
                        }
                        html += template.replace("${item.courseName}", item.courseName)
                            .replace("${item.makeUpTime}", tooltip)
                            .replace("${item.departmentName}", item.departmentName)
                            .replace("${item.property}", item.property)
                            .replace("${item.credit}", item.credit);
                    });
                    $('#otherView')[0].innerHTML = html;
                }

            } else {
                alert("网络错误！");
            }
        }
    );
},200);
