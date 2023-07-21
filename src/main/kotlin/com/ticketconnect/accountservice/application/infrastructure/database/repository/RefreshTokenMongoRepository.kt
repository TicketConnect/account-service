package com.ticketconnect.accountservice.application.infrastructure.database.repository

import com.ticketconnect.accountservice.application.infrastructure.database.document.RefreshTokenDocument
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface RefreshTokenMongoRepository : MongoRepository<RefreshTokenDocument, String> {
    fun findByToken(token: String): RefreshTokenDocument?
}