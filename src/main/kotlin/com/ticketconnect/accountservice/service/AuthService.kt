package com.ticketconnect.accountservice.service

import com.ticketconnect.accountservice.web.request.CreateUserRequest
import com.ticketconnect.accountservice.web.response.CreatedUserResponse

interface AuthService {
    fun createAccount(createUserRequest: CreateUserRequest): CreatedUserResponse
    fun generateToken(username: String): String
    fun validateToken(token: String)
}