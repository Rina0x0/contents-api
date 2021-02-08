package com.myapp.contentsapi.gateway

import com.myapp.contentsapi.domain.*
import com.myapp.contentsapi.driver.db.UserInfo
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

internal class UserInfoDomainCreatorTest {
    @Test
    fun ドメインのUsersを生成する() {
        val usersInfo = listOf(
            UserInfo().also {
                it.id = "100"
                it.firstName = "太郎1"
                it.lastName = "営業マン"
                it.department = "マンション事業部"
            },
            UserInfo().also {
                it.id = "200"
                it.firstName = "太郎2"
                it.lastName = "営業マン"
                it.department = "アパート事業部"
            },
            UserInfo().also {
                it.id = "300"
                it.firstName = "太郎3"
                it.lastName = "営業マン"
                it.department = "戸建て事業部"
            }
        )

        val expected = Users(listOf(
            User(
                Id("100"),
                FirstName("太郎1"),
                LastName("営業マン"),
                Department("マンション事業部"),
                null
            ),
            User(
                Id("200"),
                FirstName("太郎2"),
                LastName("営業マン"),
                Department("アパート事業部"),
                null
            ),
            User(
                Id("300"),
                FirstName("太郎3"),
                LastName("営業マン"),
                Department("戸建て事業部"),
                null
            )
        ))

        UserInfoDomainCreator.of(usersInfo).toUsers() shouldBeEqualTo expected
    }
}