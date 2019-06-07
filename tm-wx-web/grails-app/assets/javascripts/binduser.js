function bindUser(){
    var userId =  $("#uid").val();
    var password = $("#ups").val();
    var phone = $("#phone").val();
    var userCode = $("#userCode").val();
    if (code !== undefined && code !== null && code == userCode) {
        if (!userId) {
            alert("请正确输入" + $("#uLabel").text());
        } else if (!password) {
            alert('请输入密码！');
        } else if (!phone || phone.length !== 11) {
            alert('请正确输入手机号码！');
        } else {
            $.post("/bindUser",
                {
                    studentId: userId,
                    password: password,
                    openId: $("#openId").val(),
                    phone: phone
                },
                function(data, status) {
                    if (status === 'success') {
                        if (data.state === 'OK') {
                            alert("绑定成功！");
                            $(location).attr('href', '/' + $("#successUrl").val());
                        } else if (data.state === 'DUPLICATE') {
                            alert("你已绑定，无需重复绑定！");
                            $(location).attr('href', '/success');
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
        alert("请正确输入验证码！");
    }
}
