package com.ticketconnect.accountservice.application.security.config

import com.ticketconnect.accountservice.application.infrastructure.database.repository.AccountMongoRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class AuthConfig(
    private val accountMongoRepository: AccountMongoRepository
) {

    private val REQUIRED_AUTHENTICATION = arrayOf(
        "/shapeup/users/**",
        "/shapeup/friends/**",
        "/shapeup/profiles/**",
        "/shapeup/posts/**",
        "/shapeup/comments/**"
    )

    @Bean
    fun userDetailsService(): UserDetailsService {
        return CustomUserDetailsService(accountMongoRepository)
    }

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .httpBasic { httpBasic -> httpBasic.disable() }
            .csrf{ csrf -> csrf.disable() }
            .cors { cors -> cors.disable() }
            .sessionManagement{ sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { authorize ->
                authorize
                    .requestMatchers(HttpMethod.POST, *REQUIRED_AUTHENTICATION).authenticated()
                    .anyRequest().permitAll()
            }
            .build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationProvider(): AuthenticationProvider {
        val authenticationProvider = DaoAuthenticationProvider()
        authenticationProvider.setPasswordEncoder(passwordEncoder())
        authenticationProvider.setUserDetailsService(userDetailsService())
        return authenticationProvider
    }

    @Bean
    @Throws(Exception::class)
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager {
        return config.authenticationManager
    }
}