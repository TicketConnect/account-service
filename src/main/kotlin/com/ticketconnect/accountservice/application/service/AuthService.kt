package com.ticketconnect.accountservice.application.service

import com.ticketconnect.accountservice.web.http.request.CreateUserRequest
import com.ticketconnect.accountservice.web.http.response.CreatedUserResponse

interface AuthService {
    fun createAccount(createUserRequest: CreateUserRequest): CreatedUserResponse
    fun authenticateAccountAndReturnToken(email: String): String
    fun validateToken(token: String)
}