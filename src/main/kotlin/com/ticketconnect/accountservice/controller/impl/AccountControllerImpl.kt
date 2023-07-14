package com.ticketconnect.accountservice.controller.impl

import com.ticketconnect.accountservice.commons.log.LoggableClass
import com.ticketconnect.accountservice.controller.AccountController
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/accounts")
class AccountControllerImpl(

) : AccountController, LoggableClass() {

    override fun createAccount(): ResponseEntity<String> {

        logger.info { "RUNNING A Hello World" }

        return ResponseEntity.ok("Hello World")
    }

//    override fun getAccounts(): ResponseEntity<String> {
//        return ResponseEntity.ok("Hello World")
//    }
//
//    override fun getAccount(): ResponseEntity<String> {
//        return ResponseEntity.ok("Hello World")
//    }
//
//    override fun changeEmail(): ResponseEntity<String> {
//        return ResponseEntity.ok("Hello World")
//    }
//
//    override fun changePassword(): ResponseEntity<String> {
//        return ResponseEntity.ok("Hello World")
//    }
//
//    override fun deleteAccount(): ResponseEntity<String> {
//        return ResponseEntity.ok("Hello World")
//    }

}