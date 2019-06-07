function bindUser(){
    var phone = $("#phone").val();
    var userCode = $("#userCode").val();
    if (code !== undefined && code !== null && code == userCode) {
        if (!phone || phone.length !== 11) {
            alert('请正确输入手机号码！');
        } else {
            $.post("/bindCetUser",
                {
                    openId: $("#openId").val(),
                    phone: phone
                },
                function(data, status) {
                    if (status === 'success') {
                        if (data.state === 'OK') {
                            alert("绑定成功！");
                            $(location).attr('href', '/teacher-menu');
                        } else if (data.state === 'DUPLICATE') {
                            alert("你已绑定，无需重复绑定！");
                            $(location).attr('href', '/success');
                        } else if (data.state === 'FAIL') {
                            alert("您未报名四六级监考，请联系学院！");
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
