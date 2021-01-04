$('#confirm').on('click', function(event){
    var examId = $("#examId").val();
    var password = $("#ups").val();

    if ( !examId ) {
        alert('请输入考生号。');
    }  else if ( !password ) {
        alert('请输入身份证后6位。');
    } else {
        $.get("/qualifyExam/getExamInfo",
            {
                openId: $("#openId").val(),
                examId: examId,
                password: password
            },
            function(data, status) {
                if (status === 'success') {
                    var html = "";
                    if (data && data.examPlace) {
                        html = "<div style='margin: 2rem;'> <label>考点：</label><span>" + data.examPlace + "</span>\n" +
                            "<div> <label>姓名：</label><span>" + data.name + "</span>\n" +
                            "<div> <label>入场时间：</label><span>" + data.examTime + "</span>\n" +
                            "<div> <label>考场号：</label>第<span style='color: #ac2925'>" + data.roomId + "</span>考场\n" +
                            "<div> <label>候考室：</label><span style='color: #ac2925;'>励耘楼A111 第" + data.roomId + "组</span>\n" +
                            "<div> <label>考试科目：</label><span>" + data.type + "</span>\n" +
                            "</div>";
                    } else {
                        html = "考生号和密码不匹配！注意身份证号末位是字母的，请用大写字母。";
                    }
                    $('#otherView')[0].innerHTML = html;
                } else {
                    alert("网络错误！");
                }
            });

    }
});
