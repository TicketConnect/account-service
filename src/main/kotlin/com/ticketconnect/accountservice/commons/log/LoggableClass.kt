package com.ticketconnect.accountservice.commons.log

import mu.KotlinLogging

abstract class LoggableClass {
    companion object {
        val logger = KotlinLogging.logger {
            LoggableClass::class.java.name
        }
    }
}
