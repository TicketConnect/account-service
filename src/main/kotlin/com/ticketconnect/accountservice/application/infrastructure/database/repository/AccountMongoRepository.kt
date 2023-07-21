package com.ticketconnect.accountservice.application.infrastructure.database.repository

import com.ticketconnect.accountservice.application.infrastructure.database.document.AccountDocument
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountMongoRepository : MongoRepository<AccountDocument, String> {
    fun findByEmail(email: String): AccountDocument?
    fun existsByEmail(email: String): Boolean
}