package com.myapp.contentsapi.domain

data class Users(val list: List<User>)
data class User(val id: Id, val firstName: FirstName, val lastName: LastName, val department: Department, val case: Case?)
data class Id(val value: String)
data class Name(val value: String)
data class FirstName(val value: String)
data class LastName(val value: String)
data class Department(val value: String)
data class Case(val id: Id, val name: Name, val detail: Detaile)
data class Detaile(val value: String)
data class ContractId(val value: String)
data class UserIds(val list: List<Id>)