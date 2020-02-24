var types = [ //{id: 'sl1', label: '毕业生专用成绩单'},
    {id: 'sl2', label: '毕业证明'},
    {id: 'sl3', label: '毕业证明（第二专业）'},
    {id: 'sl4', label: '毕业证明（辅修）'},
    {id: 'sl5', label: '毕业证明（双学位）'},
    {id: 'sl6', label: '假期证明'},
    {id: 'sl7', label: '教师资格考试专用学籍证明'},
    {id: 'sl8', label: '学位证明'},
    {id: 'sl9', label: '英文成绩单（主修）'},
    {id: 'sl10', label: '英文成绩单（辅修）'},
    {id: 'sl11', label: '在校证明'},
    {id: 'sl12', label: '在校证明（合作办学）'},
    {id: 'sl13', label: '中文成绩单（辅修）'},
    {id: 'sl14', label: '中文成绩单（主修）'},
    {id: 'sl15', label: '4分制成绩单（合作办学）'},
    {id: 'sl16', label: 'CET4等级成绩证明'},
    {id: 'sl17', label: 'CET6等级成绩证明'}];
var template = "<option value=\"${typeLabel}\">${typeLabel}</option>\n";
$.get("/delay/getUserInfo",
    {
        openId: $("#openId").val(),
    },
    function(data, status) {
        if (status === 'success') {
            var html = "<select class=\"weui-select\" name=\"type\" id=\"type\">\n";
            types.forEach(function (item) {
                var selectAble = false;
                if (item.label.indexOf('辅修') >= 0) {
                    if (data.minor) {
                        selectAble = true;
                    }
                } else if (item.label.indexOf('合作办学') >= 0) {
                    if (data.abroad) {
                        selectAble = true;
                        if (item.label === '在校证明（合作办学）') {
                            if (!data.atSchool) {
                                selectAble = false;
                            }
                        }
                    }
                } else if (item.label.indexOf('毕业') >= 0) {
                    if (!data.atSchool) {
                        selectAble = true;
                    }
                } else if (item.label === '在校证明' || item.label === '假期证明') {
                    if (data.atSchool) {
                        selectAble = true;
                    }
                } else {
                    console.log(item.label);
                    selectAble = true;
                }
                if (selectAble) {
                    html += template.replace("${typeLabel}", item.label).replace("${typeLabel}", item.label);
                }

            });
            html += "</select>\n";
            $('#typeView')[0].innerHTML = html;

            $('#seal').prop("checked", data.seal);
            $('#sealComment').val(data.sealComment);
            $('#address').val(data.address);
        } else {
            alert("网络错误！");
        }
    });


$('#ok').on('click', function(event){
    var type =  $("#type").val();
    var ps = $("#ps").val();
    var phone = $("#phone").val();
    var sealComment = $("#sealComment").val();
    var seal = $("#seal").prop("checked");
    var address = $("#address").val();
    var validation = "";
    if (!type) {
        validation += "文件类型/";
    }
    if (!ps) {
        validation += "需要打印的份数/";
    }
    if (!phone) {
        validation += "联系电话/";
    }
    if (!address) {
        validation += "邮寄地址/";
    }
    if (validation && validation.length > 0) {
        alert("请正确输入：" + validation);
    } else {
        $.post("/delay",
            {
                type: type,
                ps: ps,
                openId: $("#openId").val(),
                phone: phone,
                sealComment: sealComment,
                seal: seal,
                address: address,
            },
            function(data, status) {
                if (status === 'success') {
                    if (data.state === 'OK') {
                        alert("提交成功！");
                        $("#type").val(null);
                        $("#ps").val(null);
                        showList(data.list);
                    } else {
                        alert("未知错误！");
                    }
                } else {
                    alert("网络错误！");
                }
            });
    }

});

function remove(id) {
    $.get("/delay/remove",
        {
            id: id,
            openId: $("#openId").val(),
        },
        function(data, status) {
            if (status === 'success') {
                if (data.state === 'OK') {
                    alert("删除成功！");
                    showList(data.list);
;                } else {
                    alert("删除失败！");
                }
            } else {
                alert("网络错误！");
            }
        });
}

function showList(list) {
    var html = "";
    if (list && list.length) {
        var template = "<div class=\"weui-cell\">\n" +
            "                <div class=\"weui-cell__bd\">\n" +
            "                    <p>${item.type}<span class=\"weui-badge\" style=\"margin-left: 5px;\">${item.ps}</span></p>\n" +
            "                </div>\n" +
            "            </div>\n" ;
        var removeBtn = "            <div class=\"weui-cell__ft\">\n" +
            "                <button class=\"weui-btn weui-btn_mini weui-btn_warn\" style=\"margin-right: 10px; margin-bottom: 10px\" onclick=\"remove(${item.id})\" >删除</button>\n" +
            "            </div>";
        list.forEach(function (item) {
            html += template.replace("${item.type}", item.type)
                .replace("${item.ps}", item.ps);
            if (item.removeAble) {
                html += removeBtn.replace("${item.id}", item.id);
            }
        });
    }
    $('#resultView')[0].innerHTML = html;

}
