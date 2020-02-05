package cn.edu.bnuz.bell.wx

import grails.gorm.transactions.Transactional

@Transactional
class ThemeService {

    def list() {
        Theme.executeQuery'''
select new map(
id as id,
title as title,
attendAble as attendAble
)
from Theme
where viewAble = true
'''
    }
}
