## 公众号配置
1. 服务器地址URL(能验证token的地址)
	http://es-test.bnuz.edu.cn/authTokens
2. Token：任意，但要记住，跟服务器的token值一致。
3. EncodingAESKey（消息加密密钥由43位字符组成）：随机生成

4. 页面授权（要与1.服务器地址的域名一致，**只要域名**，不要http，也不要后面的访问目录）：接口权限--网页服务--页面授权--修改
es-test.bnuz.edu.cn
*注意：个人订阅号不支持页面授权接口*
5. 接收消息的监听url：与监听Token的验证url一致，method: 'POST'
***
页面授权方法：
>>1. 引导用户进入授权页面同意授权，获取code(这一步其实就是将需要授权的页面url拼接到微信的认证请求接口里面)
>>2. 通过第一步获取的code换取网页授权access_token（与基础支持中的access_token不同）
**这一步在回调函数中实现**需要在控制器中获取微信回传给我们的code，通过这个code来请求access_token，通过access_token和openid获取用户基本信息:
