    var signUrl = window.location.href.split('#')[0];
    $.post("/config",
        {
            signUrl: signUrl
        },
        function(data, status) {
            if (status === 'success') {
                console.log(data);
                wx.config({
                    debug: false,
                    appId: data.appid,
                    timestamp: data.timestamp,
                    nonceStr: data.nonceStr,
                    signature: data.signature,
                    jsApiList: ['startRecord',
                        'stopRecord',
                        'onVoiceRecordEnd',
                        'playVoice',
                        'pauseVoice',
                        'stopVoice',
                        'onVoicePlayEnd',
                        'uploadVoice',
                        'downloadVoice'
                    ]
                })
            }
    });

    var voice = {
        localId: '',
        serverId: ''
    };

// wx.config({
//     debug: true,
//     appId: "${openid}",
//     timestamp: "${timestamp}",
//     nonceStr: "${nonceStr}",
//     signature: "${signature}",
//     jsApiList: ['scanQRCode','updateTimelineShareData','updateAppMessageShareData']
// });

wx.ready(function(){
//     // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
//
//     // 微信分享 -- 分享到朋友圈
//     wx.updateTimelineShareData({
//         title: 'test wechat share area',      // 分享标题
//         link: 'http://x.tflive.cn/wechatJsapi',       // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
//         imgUrl: 'https://www.baidu.com/img/superlogo_c4d7df0a003d3db9b65e9ef0fe6da1ec.png?where=super',     // 分享图标
//     }, function(res) {
//         //这里是回调函数
//     });
//
//     // 微信分享 -- 分享给朋友
//     wx.updateAppMessageShareData({
//         title: 'test wechat share friends', // 分享标题
//         desc: '测试', // 分享描述
//         link: 'http://x.tflive.cn/wechatJsapi', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
//         imgUrl: 'https://www.baidu.com/img/superlogo_c4d7df0a003d3db9b65e9ef0fe6da1ec.png?where=super', // 分享图标
//     }, function(res) {
//         //这里是回调函数
//     });
    //注册微信播放录音结束事件【一定要放在wx.ready函数内】
    wx.onVoicePlayEnd({
        success: function (res) {
            stopWave();
        }
    });
});

// 微信扫一扫
$(".qr_btn").on('touchstart', function(event) {
    // wx.scanQRCode({
    //     needResult: 0, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
    //     scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
    //     success: function (res) {
    //         var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
    //         console.log(result);
    //     }
    // });
    event.preventDefault();
    START = new Date().getTime();

    recordTimer = setTimeout(function(){
        wx.startRecord({
            success: function(){
                localStorage.rainAllowRecord = 'true';
            },
            cancel: function () {
                alert('用户拒绝授权录音');
            }
        });
    },300);
});

//松手结束录音
$('.qr_btn').on('touchend', function(event){
    event.preventDefault();
    END = new Date().getTime();

    if((END - START) < 300){
        END = 0;
        START = 0;
        //小于300ms，不录音
        clearTimeout(recordTimer);
    }else{
        wx.stopRecord({
            success: function (res) {
                voice.localId = res.localId;
                uploadVoice();
            },
            fail: function (res) {
                alert(JSON.stringify(res));
            }
        });
    }
});

//上传录音
function uploadVoice(){
    //调用微信的上传录音接口把本地录音先上传到微信的服务器
    //不过，微信只保留3天，而我们需要长期保存，我们需要把资源从微信服务器下载到自己的服务器
    wx.uploadVoice({
        localId: voice.localId, // 需要上传的音频的本地ID，由stopRecord接口获得
        isShowProgressTips: 1, // 默认为1，显示进度提示
        success: function (res) {
            //把录音在微信服务器上的id（res.serverId）发送到自己的服务器供下载。
            $.post("/voiceKeeper",
                {
                    serverId: res.serverId
                },function(data, status) {
                    if (status === 'success') {
                        alert("上传服务器成功");
                    }
                });
            // $.ajax({
            //     url: '后端处理上传录音的接口',
            //     type: 'post',
            //     data: JSON.stringify(res),
            //     dataType: "json",
            //     success: function (data) {
            //         alert('文件已经保存到七牛的服务器');//这回，我使用七牛存储
            //     },
            //     error: function (xhr, errorType, error) {
            //         console.log(error);
            //     }
            // });
        }
    });
}
$('#test').on('click', function(event){
    if (voice.localId === '') {
        alert('请先录制一段声音');
        return;
    }
    wx.playVoice({
        localId: voice.localId
    });
});