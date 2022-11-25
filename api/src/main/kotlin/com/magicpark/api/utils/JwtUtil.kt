package com.magicpark.api.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.magicpark.api.model.database.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component
import java.util.*


@Component
@PropertySource("classpath:application.properties")
object JwtUtil {
    private var secretKey: String = "\\$2y\\$10\$SpkyXMmmAlj9HdcdB4rqwuUg5Pm3AkhtKeE1wNG1Jxq59Pvflm9PC"

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

    private const val EXPIRE_DURATION = (24 * 60 * 60 * 1000).toLong()
}

internal inline fun <reified T> Gson.fromJson(json: String) =
    fromJson<T>(json, object : TypeToken<T>() {}.type)