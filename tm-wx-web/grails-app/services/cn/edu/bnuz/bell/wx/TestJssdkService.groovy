package cn.edu.bnuz.bell.wx

import org.springframework.beans.factory.annotation.Value

import java.security.MessageDigest

class TestJssdkService {
    @Value('${bell.appId}')
    String appId

    /**
     * 生成时间戳
     *
     * @return 时间戳
     */
    String createTimestamp() {
        return (System.currentTimeMillis() / 1000).toString()
    }

    /**
     * 生成随机字符串
     */
    String createNonceStr() {
        return UUID.randomUUID().toString()
    }

    Map<String, String> generateWxTicket(String jsApiTicket, String url) {
        Map<String, String> ret = new HashMap<>()
        String nonceStr = createNonceStr()
        String timestamp = createTimestamp()
        String string1
        String signature = ""

        string1 = "jsapi_ticket=" + jsApiTicket +
                "&noncestr=" + nonceStr +
                "&timestamp=" + timestamp +
                "&url=" + url

        println("string1 ====> " + string1)

        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1")
            crypt.reset()
            crypt.update(string1.getBytes("UTF-8"))
            signature = byteToHex(crypt.digest())
            println("signature ====> " + signature)
        } catch (Exception e) {
            e.printStackTrace()
        }

        ret.put("url", url)
        ret.put("jsapi_ticket", jsApiTicket)
        ret.put("nonceStr", nonceStr)
        ret.put("timestamp", timestamp)
        ret.put("signature", signature)
        ret.put("appid", appId)

        return ret
    }

    String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter()
        for (byte b : hash) {
            formatter.format("%02x", b)
        }
        String result = formatter.toString()
        formatter.close()
        return result
    }
}
