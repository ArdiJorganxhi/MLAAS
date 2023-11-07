package dev.ardijorganxhi.mlaas.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets

@Component
class JwtParser {

    @Value("\${jwt.token.secret}")
    private lateinit var secret: String

    fun getClaims(jwt: String): Claims {
        return Jwts.parser()
            .setSigningKey(secret.toByteArray(StandardCharsets.UTF_8))
            .parseClaimsJws(jwt)
            .body
    }
}