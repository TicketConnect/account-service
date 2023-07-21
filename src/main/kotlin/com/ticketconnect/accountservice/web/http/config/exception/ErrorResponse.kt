package com.ticketconnect.accountservice.web.http.config.exception

import java.time.LocalDateTime

data class ErrorResponse(
    val message: String,
    val statusCode: Int,
    val path: String,
    val timestamp: LocalDateTime,
)
