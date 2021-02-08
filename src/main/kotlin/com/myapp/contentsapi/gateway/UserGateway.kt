package com.myapp.contentsapi.gateway

import UserInfoDomainCreator
import com.myapp.contentsapi.domain.ContractId
import com.myapp.contentsapi.domain.Id
import com.myapp.contentsapi.domain.UserIds
import com.myapp.contentsapi.domain.Users
import com.myapp.contentsapi.driver.UserDBDriver
import com.myapp.contentsapi.driver.UserInfoDBDriver
import com.myapp.contentsapi.port.UserPort
import java.security.DomainCombiner
import javax.inject.Inject
import javax.inject.Named

@Named
class UserGateway: UserPort {
    @Inject
    lateinit var userDBDriver: UserDBDriver

    @Inject
    lateinit var userInfoDBDriver: UserInfoDBDriver

    override fun getUsers(userIds: UserIds): Users {
       return userIds.list.map { userInfoDBDriver.findOneByUserInfo(it.value) }
           .let { UserInfoDomainCreator.of(it).toUsers() }
    }
    override fun getUserIds(contractId: ContractId): UserIds {
        val userInfo = userDBDriver.findUserIdsByContractId(contractId.value.toInt())
            .map { Id(it.userId.toString()) }
        return UserIdDomainCreator.of(userInfo).toUserIds()
    }
}