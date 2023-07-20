package com.ticketconnect.accountservice.web.config.exception

import com.ticketconnect.accountservice.commons.exception.AccountAlreadyExistsException
import com.ticketconnect.accountservice.commons.exception.AccountNotFoundException
import com.ticketconnect.accountservice.commons.exception.BaseException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(AccountAlreadyExistsException::class)
    fun handleConflictException(exception: BaseException, request: HttpServletRequest): ResponseEntity<Any> {

        val errorResponse = ErrorResponse(
            message = exception.message!!,
            statusCode = HttpStatus.CONFLICT.value(),
            path = request.requestURI,
            timestamp = LocalDateTime.now()
        )

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse)
    }

    @ExceptionHandler(AccountNotFoundException::class)
    fun handleBadRequestException(exception: BaseException, request: HttpServletRequest): ResponseEntity<Any> {

        val errorResponse = ErrorResponse(
            message = exception.message!!,
            statusCode = HttpStatus.BAD_REQUEST.value(),
            path = request.requestURI,
            timestamp = LocalDateTime.now()
        )

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }
}