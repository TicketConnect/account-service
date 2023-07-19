package com.ticketconnect.accountservice.web.config.openapi

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.SpecVersion
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.tags.Tag
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenAPIConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Account Service API")
                    .description("Account Service API")
                    .version("1.0.0")
            )
            .specVersion(SpecVersion.V31)
            .addTagsItem(Tag().name("Account Service"))
    }
}