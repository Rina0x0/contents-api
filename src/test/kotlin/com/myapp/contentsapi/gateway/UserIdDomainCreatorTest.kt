package com.myapp.contentsapi.gateway

import com.myapp.contentsapi.TestBase
import com.myapp.contentsapi.domain.Id
import com.myapp.contentsapi.domain.UserIds
import org.junit.jupiter.api.Test
import org.amshove.kluent.shouldBeEqualTo

internal class UserIdDomainCreatorTest: TestBase {
    @Test
    fun userIdのリストをドメインのUserIdsに変換する() {
        val userIds = listOf(
            Id("100"),
            Id("200"),
            Id("300")
        )
        val expected = UserIds(listOf(
            Id("100"),
            Id("200"),
            Id("300")
        ))
        UserIdDomainCreator.of(userIds).toUserIds() shouldBeEqualTo expected
    }
}