package com.ticketconnect.accountservice.web.controller.impl

import com.ticketconnect.accountservice.commons.log.LoggableClass
import com.ticketconnect.accountservice.application.service.AuthService
import com.ticketconnect.accountservice.application.service.RefreshTokenService
import com.ticketconnect.accountservice.web.controller.AccountController
import com.ticketconnect.accountservice.web.request.AuthRequest
import com.ticketconnect.accountservice.web.request.CreateUserRequest
import com.ticketconnect.accountservice.web.request.RefreshTokenRequest
import com.ticketconnect.accountservice.web.response.CreatedUserResponse
import com.ticketconnect.accountservice.web.response.JwtResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AccountControllerImpl(
    private val authService: AuthService,
    private val refreshTokenService: RefreshTokenService,
    private val authenticationManager: AuthenticationManager,
) : AccountController, LoggableClass() {

    override fun createAccount(createUserRequest: CreateUserRequest): ResponseEntity<CreatedUserResponse> {

        val createdUserResponse = authService.createAccount(createUserRequest)
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserResponse)
    }

    override fun login(authRequest: AuthRequest): ResponseEntity<JwtResponse> {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.email,
                authRequest.password
            )
        )

        if (authentication.isAuthenticated) {
            val jwtToken = authService.authenticateAccountAndReturnToken(authRequest.email)
            val refreshToken = refreshTokenService.createRefreshToken(authRequest.email)

            JwtResponse(
                accessToken = jwtToken,
                token = refreshToken.token
            ).let {
                return ResponseEntity.ok(it)
            }
        } else {
            throw UsernameNotFoundException("Invalid user request")
        }
    }

    override fun validateToken(token: String): ResponseEntity<String> {
        return try {
            authService.validateToken(token)
            ResponseEntity.ok("Valid")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid")
        }
    }

    override fun refreshToken(refreshTokenRequest: RefreshTokenRequest): ResponseEntity<JwtResponse> {

        return runCatching {
            val token = refreshTokenService.findByToken(refreshTokenRequest.token)
            val verifyExpiration = refreshTokenService.verifyExpiration(token)
            val accessToken = authService.authenticateAccountAndReturnToken(verifyExpiration.accountNumber)

            ResponseEntity.ok(JwtResponse(accessToken, verifyExpiration.token))
        }.onFailure {
            throw RuntimeException("Refresh token is not in database!")
        }.getOrThrow()
    }

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