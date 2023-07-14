package com.ticketconnect.accountservice.controller.response


data class CreatedUserResponse(
    val name: String,
    val lastName: String,
    val email: String,
    val cellphone: String,
    val gender: String
)
