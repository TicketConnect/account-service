package com.ticketconnect.accountservice.infrastructure.repository

import com.ticketconnect.accountservice.infrastructure.document.RefreshTokenDocument
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface RefreshTokenMongoRepository : MongoRepository<RefreshTokenDocument, String> {
    fun findByAccountId(accountId: String): RefreshTokenDocument?
}