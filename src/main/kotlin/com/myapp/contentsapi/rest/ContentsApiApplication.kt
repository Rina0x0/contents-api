package com.myapp.contentsapi.rest

import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = ["com.myapp.contentsapi.*"])
class ContentsApiApplication

fun main(args: Array<String>) {
	System.setProperty("spring.config.location", "/home/rina/contents-api-environment/contents-api-config.yml")
	SpringApplicationBuilder()
		.sources(ContentsApiApplication::class.java)
		.web(WebApplicationType.REACTIVE)
		.run(*args)
}
