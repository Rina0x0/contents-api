package com.myapp.contentsapi.usecase

import com.myapp.contentsapi.TestBase
import com.myapp.contentsapi.domain.ContractId
import com.myapp.contentsapi.domain.UserIds
import com.myapp.contentsapi.domain.Users
import com.myapp.contentsapi.port.UserPort
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class UserUsecaseTest: TestBase {
    @InjectMockKs
    lateinit var target: UserUsecase

    @MockK
    lateinit var userPort: UserPort

    @Nested
    @DisplayName("ユーザー一覧を取得する")
    inner class GetUsers {

        @Test
        fun ユーザー情報一覧を取得する() {

            val contractId = mockk<ContractId>()
            val expected = mockk<Users>()
            val userIds = mockk<UserIds>()

            every { userPort.getUserIds(contractId) } returns userIds
            every { userPort.getUsers(userIds) } returns expected

            target.getUsers(contractId) shouldBe expected

            verify { userPort.getUserIds(contractId) }
            verify { userPort.getUsers(userIds) }

        }
    }
}