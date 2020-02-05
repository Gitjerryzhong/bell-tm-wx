function register(){
    var name =  $("#uid").val();
    var password = $("#ups").val();
    if (!name) {
        alert("请正确输入昵称");
    } else if (!password) {
        alert('请输入验证码！');
    } else {
        $.post("/register",
            {
                name: name,
                password: password,
                openId: $("#openId").val()
            },
            function(data, status) {
                if (status === 'success') {
                    if (data.state === 'OK') {
                        alert("注册成功！");
                        $(location).attr('href', '/test-menu');
                    } else if (data.state === 'DUPLICATE') {
                        alert("重名，请换一个昵称！");
                        $(location).attr('href', '/success');
                    } else if (data.state === 'FAIL') {
                        alert("验证码不正确！");
                    } else {
                        alert("未知错误！");
                    }
                } else {
                    alert("网络错误！");
                }
            });
    }
}
