//= require jquery-2.2.0.min
//= require_self

if (typeof jQuery !== 'undefined') {
    (function($) {
        $(document).ajaxStart(function() {
            $('#spinner').fadeIn();
        }).ajaxStop(function() {
            $('#spinner').fadeOut();
        });
    })(jQuery);
}

var code;
var countdown = 60;

function getCode(url){
    var phone = $("#phone").val();
    if (phone && phone.length === 11) {
        $.get(url + "?phone=" + phone, function(data, status) {
            if (status === 'success') {
                code = data.code;
                if (data.status === 'OK') {
                    var obj = $("#getCode");
                    settime(obj);
                    $("#phone").attr("disabled", true);
                } else if (data.status === 'DUPLICATED') {
                    alert('该手机号码已经绑定过！请换一个号码。');
                }
            }
        });
    } else {
        alert('请正确输入手机号码！');
    }
}

function settime(obj) {
    if (countdown == 0) {
        obj.attr('disabled', false);
        obj.text("获取验证码");
        countdown = 60;
        return;
    } else {
        obj.attr('disabled', true);
        obj.text("重新发送(" + countdown + ")");
        countdown--;
    }
    setTimeout(function () {
        settime(obj)
    }, 1000);
}
