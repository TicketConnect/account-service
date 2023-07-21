package com.ticketconnect.accountservice.application.infrastructure.kafka.message

import com.ticketconnect.accountservice.application.enums.AccountEvent

data class AccountMessage(
    val email: String,
    val cellphone: String,
    val fullName: String,
    val accountEvent: AccountEvent
) {
}