package com.ticketconnect.accountservice.application.entity

import com.ticketconnect.accountservice.application.entity.valueobject.Cellphone
import com.ticketconnect.accountservice.application.entity.valueobject.Email
import com.ticketconnect.accountservice.application.entity.valueobject.Password
import java.util.UUID

data class Account(
    val id: UUID,
    val name: String,
    val lastName: String,
    val email: Email,
    val cellphone: Cellphone,
    val gender: String,
    val password: Password
)
