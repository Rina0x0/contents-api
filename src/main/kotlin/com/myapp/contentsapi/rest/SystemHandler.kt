package com.myapp.contentsapi.rest

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.json
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono

@Component
class SystemHandler {
    fun ping(@Suppress("UNUSED_PARAMATER") request: ServerRequest): Mono<ServerResponse> {
        return ServerResponse
            .status(200)
            .json()
            .body(Mono.just("""{"pong: "ok"}"""), String::class.java)
    }
}

@Configuration
class Systemrouter(private val handler: SystemHandler) {
    @Bean
    fun systemRoutes() = router {
        GET("/v1/systems/ping", handler::ping)
    }
}