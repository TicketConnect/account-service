package com.ticketconnect.accountservice.web.http.response


data class CreatedUserResponse(
    val accountNumber: String,
    val name: String,
    val lastName: String,
    val email: String,
    val cellphone: String,
    val gender: String
)
