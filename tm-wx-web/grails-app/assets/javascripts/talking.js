$('#ok').on('click', function(event){
    var content =  $("#content").val();
    if (content) {
        $.post("/talking",
            {
                content: content,
                openId: $("#openId").val(),
                themeId: $("#themeId").val(),
            },
            function(data, status) {
                if (status === 'success') {
                    if (data) {
                        var html = "";
                        var template = "<div class=\"weui-news-inners\">\n" +
                            "            <div class=\"weui-news-info\">\n" +
                            "                <div class=\"weui-news-infoitem\">\n" +
                            "                    <span>${item.name}</span>\n" +
                            "                </div>\n" +
                            "                <div class=\"weui-news-infoitem\">${item.dateCreated}</div>\n" +
                            "            </div>\n" +
                            "            <div class=\"weui-news-text\">\n" +
                            "                <p class=\"weui-news-p\">${item.content}</p>\n" +
                            "            </div>\n" +
                            "        </div>";
                        // data.forEach(function (item) {
                        html += template.replace("${item.name}", data.name)
                            .replace("${item.dateCreated}", data.dateCreated)
                            .replace("${item.content}", data.content);
                        // });
                        // $('#view')[0].innerHTML = html;
                        $('#view').append(html);
                        $("#content").val('');
                    }
                } else {
                    alert("网络错误！");
                }
            });
    }
});
$('#searchBtn').on('click', function(event){
    var q =  $("#search").val();
    if (q) {
        $.get("/talking/search",
            {
                q: q,
                openId: $("#openId").val(),
                themeId: $("#themeId").val(),
            },
            function (data, status) {
                if (status === 'success') {
                    if (data && data.length) {
                        var html = "";
                        var template = "<div class=\"weui-news-inners\">\n" +
                            "            <div class=\"weui-news-info\">\n" +
                            "                <div class=\"weui-news-infoitem\">\n" +
                            "                    <span>${item.name}</span>\n" +
                            "                </div>\n" +
                            "                <div class=\"weui-news-infoitem\">${item.dateCreated}</div>\n" +
                            "            </div>\n" +
                            "            <div class=\"weui-news-text\">\n" +
                            "                <p class=\"weui-news-p\">${item.content}</p>\n" +
                            "            </div>\n" +
                            "        </div>";
                        data.forEach(function (item) {
                            html += template.replace("${item.name}", item.name)
                                .replace("${item.dateCreated}", item.dateCreated)
                                .replace("${item.content}", item.content);
                            });
                        $('#view')[0].innerHTML = html;
                    }
                }
            });
    }
});