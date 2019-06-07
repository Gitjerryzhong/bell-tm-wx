function bindNewPhone(){
    var password = $("#ups").val();
    var phone = $("#phone").val();
    var userCode = $("#userCode").val();
    if (code !== undefined && code !== null && code == userCode) {
        if (!password) {
            alert('请输入密码！');
        } else if (!phone || phone.length !== 11) {
            alert('请正确输入手机号码！');
        } else {
            $.post("/bindNewPhone",
                {
                    password: password,
                    openId: $("#openId").val(),
                    phone: phone
                },
                function(data, status) {
                    if (status === 'success') {
                        if (data.state === 'OK') {
                            alert("绑定成功！");
                            $(location).attr('href', '/' + $("#successUrl").val());
                        } else if (data.state === 'FAIL') {
                            alert("用户名密码不正确！");
                        } else {
                            alert("未知错误！");
                        }
                    } else {
                        alert("网络错误！");
                    }
                });
        }
    } else {
        alert("code:" + code + ", userCode: " + userCode);
    }
}
