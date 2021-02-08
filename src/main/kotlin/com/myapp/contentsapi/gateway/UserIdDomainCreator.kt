package com.myapp.contentsapi.gateway

import com.myapp.contentsapi.domain.Id
import com.myapp.contentsapi.domain.UserIds
import com.myapp.contentsapi.domain.Users
import com.myapp.contentsapi.driver.db.UserInfo

class UserIdDomainCreator(val userIds: List<Id>) {
    companion object {
        fun of(userIds: List<Id>): UserIdDomainCreator {
            return UserIdDomainCreator(userIds)
        }
    }
    fun toUserIds(): UserIds {
        return userIds.map { Id(it.value) }.let(::UserIds)
    }
}