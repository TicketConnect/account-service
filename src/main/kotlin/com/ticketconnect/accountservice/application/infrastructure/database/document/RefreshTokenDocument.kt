package com.ticketconnect.accountservice.application.infrastructure.database.document

import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "refresh_token")
data class RefreshTokenDocument(
    @Field("account_number")
    val accountNumber: String,
    @Field("token")
    val token: String,
    @Field("expiry_date")
    val expiryDate: Instant
)
