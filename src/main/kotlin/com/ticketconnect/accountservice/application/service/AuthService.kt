package com.ticketconnect.accountservice.application.service

import com.ticketconnect.accountservice.web.request.CreateUserRequest
import com.ticketconnect.accountservice.web.response.CreatedUserResponse

interface AuthService {
    fun createAccount(createUserRequest: CreateUserRequest): CreatedUserResponse
    fun authenticateAccountAndReturnToken(email: String): String
    fun validateToken(token: String)
}