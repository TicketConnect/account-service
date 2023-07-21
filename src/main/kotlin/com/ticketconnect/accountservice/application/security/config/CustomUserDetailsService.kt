package com.ticketconnect.accountservice.application.security.config

import com.ticketconnect.accountservice.application.infrastructure.database.repository.AccountMongoRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class CustomUserDetailsService(
    private val repository: AccountMongoRepository
): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val account = repository.findByEmail(username)
            ?: throw UsernameNotFoundException("user not found with name: $username")

        return CustomUserDetails(account)
    }
}