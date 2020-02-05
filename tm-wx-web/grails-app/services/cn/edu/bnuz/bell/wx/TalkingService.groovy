package cn.edu.bnuz.bell.wx

import cn.edu.bnuz.bell.cmd.TalkingCommand
import grails.gorm.transactions.Transactional

import java.text.SimpleDateFormat

@Transactional
class TalkingService {

    def list(String openId, Long themeId) {
        def list = Talking.executeQuery'''
select new map(
tu.name as name,
t.content as content,
to_char(t.dateCreated, 'YYYY-MM-DD') as dateCreated
)
from Theme th
join th.permission p
join th.talking t
join p.user u
join t.user tu
where th.id = :themeId
and th.viewAble is true
and u.openId = :openId
and p.viewAble is true
and t.deleted is not true
order by t.id
''', [themeId: themeId, openId: openId]
        def permission = ThemePermission.findByThemeAndUser(Theme.load(themeId), NewFireUser.findByOpenId(openId))
        return [permission: permission, list: list]
    }

    def save(TalkingCommand cmd) {
        Talking talking = new Talking(
                user: NewFireUser.findByOpenId(cmd.openId),
                content: cmd.content,
                deleted: false,
                dateCreated: new Date(),
                theme: Theme.load(cmd.themeId)
        )
        if (!talking.save()) {
            talking.errors.each {
                println it
            }
        }
        return [name: talking.user.name,
                content: talking.content,
                dateCreated: new SimpleDateFormat('yyyy-MM-dd').format(talking.dateCreated)]
    }

    def search(String openId, Long themeId, String q) {
        Talking.executeQuery'''
select new map(
tu.name as name,
t.content as content,
to_char(t.dateCreated, 'YYYY-MM-DD') as dateCreated
)
from Theme th
join th.permission p
join th.talking t
join p.user u
join t.user tu
where th.id = :themeId
and th.viewAble is true
and u.openId = :openId
and p.viewAble is true
and t.deleted is not true
and (tu.name like :q or t.content like :q)
order by t.id
''', [themeId: themeId, openId: openId, q: "%${q}%"]
    }
}
