package com.ticketconnect.accountservice.web.controller

import com.ticketconnect.accountservice.web.request.AuthRequest
import com.ticketconnect.accountservice.web.request.CreateUserRequest
import com.ticketconnect.accountservice.web.response.CreatedUserResponse
import com.ticketconnect.accountservice.web.response.JwtResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

interface AccountController {

    @PostMapping("/register")
    fun createAccount(@RequestBody createUserRequest: CreateUserRequest): ResponseEntity<CreatedUserResponse>

    @GetMapping("/login")
    fun getToken(@RequestBody authRequest: AuthRequest): ResponseEntity<JwtResponse>

    @GetMapping("/validade")
    fun validateToken(@RequestParam("token") token: String): ResponseEntity<String>


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