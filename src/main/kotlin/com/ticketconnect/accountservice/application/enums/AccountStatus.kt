package com.ticketconnect.accountservice.application.enums

enum class AccountStatus(val statusId: Int) {
    PENDING_CONFIRMATION(1),
    ACTIVE(2),
    DELETED(3);

    companion object {
        fun toEnum(statusId: Int): AccountStatus =
            values().find { it.statusId == statusId } ?: throw IllegalArgumentException("Invalid statusId: $statusId")
    }
}
