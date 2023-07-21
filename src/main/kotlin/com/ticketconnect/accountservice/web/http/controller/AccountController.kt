package com.ticketconnect.accountservice.web.http.controller

import com.ticketconnect.accountservice.web.http.request.AuthRequest
import com.ticketconnect.accountservice.web.http.request.CreateUserRequest
import com.ticketconnect.accountservice.web.http.request.RefreshTokenRequest
import com.ticketconnect.accountservice.web.http.response.CreatedUserResponse
import com.ticketconnect.accountservice.web.http.response.JwtResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

interface AccountController {
    @PostMapping("/register")
    fun createAccount(@RequestBody createUserRequest: CreateUserRequest): ResponseEntity<CreatedUserResponse>

    @PostMapping("/login")
    fun login(@RequestBody authRequest: AuthRequest): ResponseEntity<JwtResponse>

    @PostMapping("/validate")
    fun validateToken(@RequestParam("token") token: String): ResponseEntity<String>

    @PostMapping("/refresh-token")
    fun refreshToken(@RequestBody refreshTokenRequest: RefreshTokenRequest): ResponseEntity<JwtResponse>
}
