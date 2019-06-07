package cn.edu.bnuz.bell.wx

import cn.edu.bnuz.bell.wx.AccessToken
import grails.gorm.transactions.Transactional
import javassist.tools.web.BadHttpRequest

import java.security.MessageDigest

@Transactional
class AuthTokenService {

    def getSha1(String str) {
        if (!str || str.length() == 0) {
            return null
        }
        def hexDigits = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                         'a', 'b', 'c', 'd', 'e', 'f']
        try {
            MessageDigest mdTemp = MessageDigest.getInstance('SHA1');
            mdTemp.update(str.getBytes('UTF-8'))
            byte[] md = mdTemp.digest()
            int j = md.length
            char[] buf = new char[j*2]
            int k = 0
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i]
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf]
                buf[k++] = hexDigits[byte0 & 0xf]
            }
            return new String(buf)
        } catch (Exception e) {
            return null
        }

    }

    def findToken(String appId, String appSecret) {
        def tokens = AccessToken.executeQuery'''
select token
from AccessToken 
where appId = :appId and appSecret = :appSecret and createdTime > :currentTime - expiresIn *1000
order by createdTime desc
''', [appId: appId, appSecret: appSecret, currentTime: new Date().time], [max: 1]
        if (tokens) {
            return tokens[0]
        }
    }

    def saveAccessToken(accessToken, String appId, String appSecret) {
        if (!accessToken) {
            throw new BadHttpRequest()
        }
        def item = new AccessToken(
                token: accessToken.access_token,
                expiresIn: accessToken.expires_in,
                appId: appId,
                appSecret: appSecret,
                createdTime: new Date().time
        )
        if (!item.save()) {
            item.errors.each {
                println it
            }
        }
    }
}
