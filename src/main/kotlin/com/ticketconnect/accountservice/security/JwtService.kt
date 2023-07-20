package com.ticketconnect.accountservice.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*

@Service
class JwtService {

    private val expirationDateInMillisSecond = Date(System.currentTimeMillis() + 1000 * 60 * 2)
    private val issueDateInMillisSecond = Date(System.currentTimeMillis())

    fun validateToken(token: String) {
        Jwts.parser().setSigningKey(getSignKey()).parseClaimsJws(token)
    }

    fun generateToken(subject: String): String {
        val claims: Map<String, Any> = HashMap()
        return createToken(claims, subject)
    }

    private fun createToken(claims: Map<String, Any>, subject: String): String {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(issueDateInMillisSecond)
            .setExpiration(expirationDateInMillisSecond)
            .signWith(SignatureAlgorithm.HS256, getSignKey())
            .compact()
    }

    private fun getSignKey(): Key {
        val keyBytes = Base64.getDecoder().decode(SECRET)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    companion object {
        private const val SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437"
    }
}