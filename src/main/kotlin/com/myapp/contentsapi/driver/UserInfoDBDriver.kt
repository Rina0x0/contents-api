package com.myapp.contentsapi.driver

import com.myapp.contentsapi.driver.db.UserInfo
import javax.inject.Named

// 本当はここではJPAをつかってDBからEntityをとってくるのだが
// 書きっぷりを試したいだけの仮プロジェクトなので
// JPAを継承したりはしない
@Named
class UserInfoDBDriver {
    fun findOneByUserInfo(id: String): UserInfo {
        TODO()
    }
}