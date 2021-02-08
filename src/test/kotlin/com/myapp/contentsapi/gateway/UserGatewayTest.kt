package com.myapp.contentsapi.gateway

import UserInfoDomainCreator
import com.myapp.contentsapi.TestBase
import com.myapp.contentsapi.domain.ContractId
import com.myapp.contentsapi.domain.Id
import com.myapp.contentsapi.domain.UserIds
import com.myapp.contentsapi.domain.Users
import com.myapp.contentsapi.driver.UserDBDriver
import com.myapp.contentsapi.driver.UserInfoDBDriver
import com.myapp.contentsapi.driver.db.User
import com.myapp.contentsapi.driver.db.UserInfo
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class UserGatewayTest: TestBase{

    @InjectMockKs
    lateinit var target: UserGateway

    @MockK
    lateinit var userDBDriver: UserDBDriver

    @MockK
    lateinit var userInfoDBDriver: UserInfoDBDriver

    @Nested
    @DisplayName("ContractIdに紐づくUserIdsを取得する")
    inner class GetUserIds {

        @Test
        fun ContractIdを指定してUserIdsを全て取得する() {

            mockkObject(UserIdDomainCreator.Companion)

            val contractId = ContractId("9999")
            val expected = mockk<UserIds>()
            val userDomainCreator = mockk<UserIdDomainCreator>()
            val userInfoEntity = listOf(
                User().also {
                    it.contractId = 1111
                    it.userId = 100
                },
                User().also {
                    it.contractId = 1111
                    it.userId = 200
                },
                User().also {
                    it.contractId = 1111
                    it.userId = 300
                }
            )

            val ids = listOf<Id>(
                Id("100"),
                Id("200"),
                Id("300")
            )

            every { userDBDriver.findUserIdsByContractId(contractId.value.toInt()) } returns userInfoEntity
            every { UserIdDomainCreator.of(ids)} returns userDomainCreator
            every { userDomainCreator.toUserIds() } returns expected

            target.getUserIds(contractId) shouldBe expected

            verify { userDBDriver.findUserIdsByContractId(contractId.value.toInt()) }
            verify { UserIdDomainCreator.of(ids) }
            verify { userDomainCreator.toUserIds() }
        }

        @Test
        fun IDを指定してユーザー情報を全て取得する() {

            mockkObject(UserInfoDomainCreator.Companion)

            val id1 = Id("100")
            val id2 = Id("200")
            val id3 = Id("300")
            val userIds = UserIds(listOf(id1, id2,id3))
            val expected = mockk<Users>()

            val userInfo1 = UserInfo().also {
                it.id = "100"
                it.firstName = "太郎1"
                it.lastName = "営業マン"
                it.department = "マンション事業部"
            }
            val userInfo2 = UserInfo().also {
                it.id = "200"
                it.firstName = "太郎2"
                it.lastName = "営業マン"
                it.department = "戸建て事業部"
            }
            val userInfo3 = UserInfo().also {
                it.id = "100"
                it.firstName = "太郎3"
                it.lastName = "営業マン"
                it.department = "アパート事業部"
            }

            val usersInfo = listOf(userInfo1, userInfo2, userInfo3)
            val creator = mockk<UserInfoDomainCreator>()

            every { userInfoDBDriver.findOneByUserInfo(id1.value) } returns userInfo1
            every { userInfoDBDriver.findOneByUserInfo(id2.value) } returns userInfo2
            every { userInfoDBDriver.findOneByUserInfo(id3.value) } returns userInfo3
            every { UserInfoDomainCreator.of(usersInfo) } returns creator
            every { creator.toUsers() } returns expected

            target.getUsers(userIds) shouldBe expected

            verify { userInfoDBDriver.findOneByUserInfo(id1.value) }
            verify { userInfoDBDriver.findOneByUserInfo(id2.value) }
            verify { userInfoDBDriver.findOneByUserInfo(id3.value) }
            verify { UserInfoDomainCreator.of(usersInfo) }
            verify {  creator.toUsers() }
        }
    }
}