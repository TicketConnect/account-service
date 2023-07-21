package com.ticketconnect.accountservice.application.service

import com.ticketconnect.accountservice.application.infrastructure.database.document.RefreshTokenDocument

interface RefreshTokenService {
    fun createRefreshToken(email: String): RefreshTokenDocument
    fun findByToken(token: String): RefreshTokenDocument
    fun verifyExpiration(token: RefreshTokenDocument): RefreshTokenDocument
}