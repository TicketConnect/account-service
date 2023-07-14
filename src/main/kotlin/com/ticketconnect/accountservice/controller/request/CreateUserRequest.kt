package com.ticketconnect.accountservice.controller.request

data class CreateUserRequest(
    val name: String,
    val lastName: String,
    val email: String,
    val cellphone: String,
    val password: String,
    val gender: String
)
