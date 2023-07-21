package com.ticketconnect.accountservice.web.http.request

data class AuthRequest(
    val email: String,
    val password: String
)
