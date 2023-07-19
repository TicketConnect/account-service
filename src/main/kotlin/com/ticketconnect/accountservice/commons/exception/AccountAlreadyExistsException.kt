package com.ticketconnect.accountservice.commons.exception

class AccountAlreadyExistsException(
    override val message: String = "Account already exists",
    override val cause: Throwable? = null
): BaseException(message, cause)
