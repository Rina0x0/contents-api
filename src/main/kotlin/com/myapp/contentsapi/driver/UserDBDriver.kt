package com.myapp.contentsapi.driver

import com.myapp.contentsapi.driver.db.User
import javax.inject.Named

// 本当はここではJPAをつかってDBからEntityをとってくるのだが
// 書きっぷりを試したいだけの仮プロジェクトなので
// JPAを継承したりはしない
@Named
class UserDBDriver {
    fun findUserIdsByContractId(contractId: Int) : List<User> {
        TODO()
    }
}