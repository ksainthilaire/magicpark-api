package com.magicpark.api.utils

import com.magicpark.api.model.database.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*


@Component
class JwtUtil {
    @Value("\${app.jwt.secret}")
    private val secretKey: String? = null

    fun generateAccessToken(user: User): String {
        return Jwts.builder()
            .setSubject(java.lang.String.format("%s,%s", user.id, user.mail))
            .setIssuer("CodeJava")
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + EXPIRE_DURATION))
            .signWith(SignatureAlgorithm.HS512, secretKey)
            .compact()
    }

    fun validateAccessToken(token: String?): Boolean {
        return try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun getSubject(token: String): String {
        return parseClaims(token).subject
    }

    private fun parseClaims(token: String): Claims {
        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .body
    }

    companion object {
        private const val EXPIRE_DURATION = (24 * 60 * 60 * 1000).toLong()
    }
}