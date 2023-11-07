package dev.ardijorganxhi.mlaas.config

import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import java.nio.charset.StandardCharsets
import javax.crypto.spec.SecretKeySpec

@Configuration
class JwtConfiguration {

    @Value("\${jwt.token.secret}")
    private val secret: String? = null

    fun getSecretKeySpec(): SecretKeySpec {
        return try {
            SecretKeySpec(secret?.toByteArray(StandardCharsets.UTF_8), SignatureAlgorithm.HS512.jcaName)
        } catch (e: Exception) {
            throw Exception(e.message)
        }
    }
}