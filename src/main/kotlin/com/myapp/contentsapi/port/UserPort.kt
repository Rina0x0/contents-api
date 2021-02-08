package com.myapp.contentsapi.port

import com.myapp.contentsapi.domain.ContractId
import com.myapp.contentsapi.domain.UserIds
import com.myapp.contentsapi.domain.Users

interface UserPort {
    fun getUsers(userIds: UserIds): Users
    fun getUserIds(contractId: ContractId): UserIds
}