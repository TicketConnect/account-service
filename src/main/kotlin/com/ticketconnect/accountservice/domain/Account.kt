package com.ticketconnect.accountservice.domain

import com.ticketconnect.accountservice.domain.valueobject.Cellphone
import com.ticketconnect.accountservice.domain.valueobject.Email
import com.ticketconnect.accountservice.domain.valueobject.Password
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
