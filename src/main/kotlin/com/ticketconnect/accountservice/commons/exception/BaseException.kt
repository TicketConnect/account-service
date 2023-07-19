package com.ticketconnect.accountservice.commons.exception

open class BaseException(
    override val message: String? = null,
    override val cause: Throwable? = null
): RuntimeException(message, cause)