package com.ticketconnect.accountservice.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping

interface AccountController {

    @PostMapping
    fun createAccount(): ResponseEntity<String>

//    @GetMapping
//    fun getAccounts(): ResponseEntity<String>
//
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