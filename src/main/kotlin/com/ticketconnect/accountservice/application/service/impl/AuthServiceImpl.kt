package com.ticketconnect.accountservice.application.service.impl

import com.ticketconnect.accountservice.commons.exception.AccountAlreadyExistsException
import com.ticketconnect.accountservice.commons.exception.AccountNotFoundException
import com.ticketconnect.accountservice.application.infrastructure.database.document.AccountDocument
import com.ticketconnect.accountservice.web.http.request.CreateUserRequest
import com.ticketconnect.accountservice.web.http.request.CreateUserRequest.Companion.toDocument
import com.ticketconnect.accountservice.web.http.response.CreatedUserResponse
import com.ticketconnect.accountservice.application.infrastructure.database.repository.AccountMongoRepository
import com.ticketconnect.accountservice.application.security.JwtService
import com.ticketconnect.accountservice.application.service.AuthService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
    private val accountMongoRepository: AccountMongoRepository
): AuthService {

    val logger: Logger = LoggerFactory.getLogger(AuthServiceImpl::class.java)
    override fun createAccount(createUserRequest: CreateUserRequest): CreatedUserResponse {

        return runCatching {
            verifyAccountAlreadyExistsAndThrowException(createUserRequest.email)

            val encodedPassword = passwordEncoder.encode(createUserRequest.password)
            createUserRequest.password = encodedPassword
            val accountDocument = createUserRequest.toDocument()

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

    override fun authenticateAccountAndReturnToken(email: String): String {
        return runCatching {
            val accountDocument = verifyAccountAlreadyExistsAndReturnAccount(email)

            jwtService.generateToken(accountDocument.accountNumber)
        }.onFailure { exception ->
            logger.info("Error authenticating account: ${exception.message}")
            when (exception) {
                is AccountNotFoundException -> throw AccountNotFoundException(cause = exception.cause)
            }
        }.getOrThrow()
    }

    override fun validateToken(token: String) {
        jwtService.validateToken(token)
    }

    private fun consultAccountInDatabase(email: String): AccountDocument? {
        return accountMongoRepository.findByEmail(email)
    }

    private fun verifyAccountAlreadyExistsAndThrowException(email: String) {
        if(consultAccountInDatabase(email) != null) {
            logger.info("Account already exists")
            throw AccountAlreadyExistsException()
        }
    }

    private fun verifyAccountAlreadyExistsAndReturnAccount(email: String): AccountDocument {
        return consultAccountInDatabase(email) ?: throw AccountNotFoundException()
    }
}