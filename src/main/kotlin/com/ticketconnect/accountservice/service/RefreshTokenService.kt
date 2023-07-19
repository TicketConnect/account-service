package com.ticketconnect.accountservice.service

import com.ticketconnect.accountservice.infrastructure.document.RefreshTokenDocument

interface RefreshTokenService {
    fun createRefreshToken(email: String): RefreshTokenDocument
}