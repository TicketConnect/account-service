package com.ticketconnect.accountservice.web.request

data class AuthRequest(
    val email: String,
    val password: String
)
