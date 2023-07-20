package com.ticketconnect.accountservice.web.controller

import com.ticketconnect.accountservice.web.request.AuthRequest
import com.ticketconnect.accountservice.web.request.CreateUserRequest
import com.ticketconnect.accountservice.web.request.RefreshTokenRequest
import com.ticketconnect.accountservice.web.response.CreatedUserResponse
import com.ticketconnect.accountservice.web.response.JwtResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

interface AccountController {

    @PostMapping("/register")
    fun createAccount(@RequestBody createUserRequest: CreateUserRequest): ResponseEntity<CreatedUserResponse>

    @PostMapping("/login")
    fun login(@RequestBody authRequest: AuthRequest): ResponseEntity<JwtResponse>

    @PostMapping("/validade")
    fun validateToken(@RequestParam("token") token: String): ResponseEntity<String>

    @PostMapping("/refresh-token")
    fun refreshToken(@RequestBody refreshTokenRequest: RefreshTokenRequest): ResponseEntity<JwtResponse>

//    @GetMapping
//    fun getAccount(): ResponseEntity<String>
//
//    @PutMapping
//    fun changeEmail(): ResponseEntity<String>
//
//    @PutMapping
//    fun changePassword(): ResponseEntity<String>
//
//    @DeleteMapping
//    fun deleteAccount(): ResponseEntity<String>
}