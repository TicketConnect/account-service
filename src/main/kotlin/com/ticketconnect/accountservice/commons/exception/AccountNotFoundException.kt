package com.ticketconnect.accountservice.commons.exception

class AccountNotFoundException(
    override val message: String = "Account not found",
    override val cause: Throwable? = null
): BaseException(message, cause)
