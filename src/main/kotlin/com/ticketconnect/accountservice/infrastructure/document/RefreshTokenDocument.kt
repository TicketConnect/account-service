package com.ticketconnect.accountservice.infrastructure.document

import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "refresh_token")
data class RefreshTokenDocument(
    val accountNumber: String,
    val token: String,
    val expiryDate: Instant
)
