package com.myapp.contentsapi.rest

import com.myapp.contentsapi.domain.ContractId
import com.myapp.contentsapi.domain.User
import com.myapp.contentsapi.domain.Users
import com.myapp.contentsapi.usecase.UserUsecase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono
import javax.inject.Inject
import javax.inject.Named

@Named
class SalesmanHandler {
    @Inject
    private lateinit var userUsecase: UserUsecase

    fun getSalesmans(@Suppress("UNUSED_PARAMETER") request: ServerRequest): Mono<ServerResponse> {
        val contractId = request.headers().header("test-headers").firstOrNull()?.let { ContractId(it) }
        if(contractId == null) {
            throw Exception("Header is Null.")
        }
        return try {
            val users = userUsecase.getUsers(contractId)
            ServerResponse.status(200).body(Mono.just(users.toJson()), SalesmansJson::class.java)
        } catch (e: Exception) {
            ServerResponse.status(500).body(Mono.just("Get Salesmans Exception"))
        }
    }
}

@Configuration
class SalesmanResource(private val handler: SalesmanHandler) {
    @Bean
    fun salesmanRouter() = router {
        (accept(MediaType.APPLICATION_JSON)).nest {
            GET("/v1/salesmans", handler::getSalesmans)
        }
    }
}

data class SalesmansJson(val list: List<SalesmanJson>)
data class SalesmanJson(val id: String, val firstName: String, val lastName: String, val department: String)

fun Users.toJson(): SalesmansJson {
    return list.map { it.toJson() }.let(::SalesmansJson)
}

fun User.toJson(): SalesmanJson {
    return SalesmanJson(
        id.value,
        firstName.value,
        lastName.value,
        department.value
    )
}