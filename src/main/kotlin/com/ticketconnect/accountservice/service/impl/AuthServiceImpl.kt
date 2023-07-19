package com.ticketconnect.accountservice.service.impl

import com.ticketconnect.accountservice.commons.exception.AccountAlreadyExistsException
import com.ticketconnect.accountservice.web.request.CreateUserRequest
import com.ticketconnect.accountservice.web.request.CreateUserRequest.Companion.toDocument
import com.ticketconnect.accountservice.web.response.CreatedUserResponse
import com.ticketconnect.accountservice.infrastructure.repository.AccountMongoRepository
import com.ticketconnect.accountservice.security.JwtService
import com.ticketconnect.accountservice.service.AuthService
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
    private val accountMongoRepository: AccountMongoRepository
): AuthService {

    val logger = LoggerFactory.getLogger(AuthServiceImpl::class.java)
    override fun createAccount(creeateUserRequest: CreateUserRequest): CreatedUserResponse {

        return runCatching {
            validateAccountExists(creeateUserRequest.email)

            val encodedPassword = passwordEncoder.encode(creeateUserRequest.password)
            creeateUserRequest.password = encodedPassword
            val accountDocument = creeateUserRequest.toDocument()

            accountMongoRepository.save(accountDocument)
            CreatedUserResponse(
                accountNumber = accountDocument.accountNumber,
                name = accountDocument.name,
                lastName = accountDocument.lastName,
                email = accountDocument.email,
                cellphone = accountDocument.cellphone,
                gender = accountDocument.gender
            )

        }.onFailure { exception ->
            logger.info("Error creating account: ${exception.message}")
            when (exception) {
                is AccountAlreadyExistsException -> throw AccountAlreadyExistsException(cause = exception.cause)
            }
        }.getOrThrow()
    }

    override fun generateToken(username: String): String {
        return jwtService.generateToken(username)
    }

    override fun validateToken(token: String) {
        jwtService.validateToken(token)
    }

    private fun accountExists(email: String): Boolean {
        return accountMongoRepository.existsByEmail(email)
    }

    private fun validateAccountExists(email: String) {
        if(accountExists(email)) {
            throw AccountAlreadyExistsException()
        }
    }
}