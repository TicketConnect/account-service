package com.ticketconnect.accountservice.service.impl

import com.ticketconnect.accountservice.commons.log.LoggableClass
import com.ticketconnect.accountservice.infrastructure.document.RefreshTokenDocument
import com.ticketconnect.accountservice.infrastructure.repository.AccountMongoRepository
import com.ticketconnect.accountservice.infrastructure.repository.RefreshTokenMongoRepository
import com.ticketconnect.accountservice.service.RefreshTokenService
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class RefreshTokenServiceImpl(
    private val refreshTokenMongoRepository: RefreshTokenMongoRepository,
    private val accountMongoRepository: AccountMongoRepository
) : RefreshTokenService, LoggableClass() {

    override fun createRefreshToken(email: String): RefreshTokenDocument {

        return runCatching {
            val accountId = accountMongoRepository.findByEmail(email)!!.email
            val expiryDate = Instant.now().plusMillis(600000)

            val refreshTokenDocument = RefreshTokenDocument(
                accountId = accountId,
                token = UUID.randomUUID().toString(),
                expiryDate = expiryDate
            )

            refreshTokenMongoRepository.save(refreshTokenDocument)
        }.onSuccess {
            logger.info("Refresh token created successfully")
        }.onFailure {
            logger.error("Error creating refresh token. Error: ${it.message}")
        }.getOrThrow()
    }

}