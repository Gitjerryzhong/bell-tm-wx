$('#confirm').on('click', function(event){
    var email = $("#email").val();
    var phone = $("#phone").val();
    var flag = $("#flag").val();
    var userCode = $("#userCode").val();
    var validPhone = $("#validPhone").val();

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
    } else if (flag === '0') {
        alert('请选择是否参加考试。');
    }else {
        $.post("/degreeEnglish",
            {
                openId: $("#openId").val(),
                phone: validPhone,
                email: email,
                flag:   flag
            },
            function(data, status) {
                if (status === 'success') {
                    if (data.state === 'OK') {
                        alert("报名成功！");
                        $(location).attr('href', '/student-menu');
                    } else if (data.state === 'FAIL') {
                        alert("用户无此次报名资格！");
                    } else if (data.state === 'EXPIRE') {
                        alert("目前不是报名时间，请留意通知！");
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