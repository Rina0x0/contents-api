package com.myapp.contentsapi.usecase

import com.myapp.contentsapi.domain.ContractId
import com.myapp.contentsapi.domain.Users
import com.myapp.contentsapi.port.UserPort
import javax.inject.Inject
import javax.inject.Named

@Named
class UserUsecase {
    @Inject
    lateinit var userPort: UserPort

    fun getUsers(contractId: ContractId): Users {
        val userIds = userPort.getUserIds(contractId)
        return userPort.getUsers(userIds)
    }
}