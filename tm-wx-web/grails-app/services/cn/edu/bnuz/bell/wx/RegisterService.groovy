package cn.edu.bnuz.bell.wx

import cn.edu.bnuz.bell.cmd.NewFireUserCommand
import grails.gorm.transactions.Transactional

@Transactional
class RegisterService {

    def isAdmin(String openId) {
        def user = NewFireUser.findByOpenId(openId)
        user?.type == 'ADMIN'
    }

    def hasRegisted(String openId) {
        NewFireUser.findByOpenId(openId) != null
    }

    def getRegistryKey() {
        def result = RegistryKey.executeQuery'''
select key
from RegistryKey 
where createdTime > :currentTime - expiresIn *1000
order by createdTime desc
''', [currentTime: new Date().time], [max: 1]
        if (result) {
            return result[0]
        } else {
            def key = Math.floor(Math.random() * 1000000) as Integer
            def registryKey = new RegistryKey(
                    key: key,
                    createdTime: new Date().time,
                    expiresIn: 600
            )
            if (!registryKey.save()) {
                registryKey.errors.each {
                    println it
                }
            }
            return key
        }
    }

    def regist(NewFireUserCommand cmd) {
        def result = RegistryKey.executeQuery'''
select id
from RegistryKey
where key = :key and createdTime > :currentTime - expiresIn *1000
''', [key: cmd.password, currentTime: new Date().time]
        if (result) {
            def user = new NewFireUser(
                    registryKey: RegistryKey.load(result[0] as Long),
                    openId: cmd.openId,
                    dateCreated: new Date(),
                    type: 'USER',
                    name: cmd.name,
                    enabled: true
            )
            user.id = UUID.randomUUID().toString()
            if (!user.save()) {
                user.errors.each {
                    println it
                }
                return 'FAIL'
            }
            // 默认都有亲子教育的讨论权限
            def permission = new ThemePermission(
                    user: user,
                    viewAble: true,
                    speakAble: true,
                    type: 'USER',
                    theme: Theme.load(1)
            )
            if (!permission.save()) {
                permission.errors.each {
                    println it
                }
            }
            return 'OK'
        } else {
            return 'FAIL'
        }
    }
}
