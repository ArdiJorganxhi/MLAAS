package dev.ardijorganxhi.mlaas.service

import dev.ardijorganxhi.mlaas.config.JwtConfiguration
import dev.ardijorganxhi.mlaas.model.dto.UserDto
import dev.ardijorganxhi.mlaas.utils.DateUtils
import io.jsonwebtoken.Claims
import io.jsonwebtoken.CompressionCodecs
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.apache.commons.lang3.ArrayUtils
import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import javax.servlet.http.HttpServletRequest
import kotlin.collections.HashMap

@Service
class TokenService(private val jwtConfiguration: JwtConfiguration, private val jwtParser: JwtParser) {
    companion object {
        const val BEARER = "Bearer "
        private const val TOKEN_EXPIRATION_HOURS_COUNT = 9
    }

    fun createToken(userCreateDto: UserDto): String {
        val tokenData = HashMap<String, Any>()
        tokenData["email"] = userCreateDto.email
        tokenData["id"] = userCreateDto.id
        val now = LocalDateTime.now()
        return BEARER + Jwts.builder()
            .addClaims(tokenData)
                .setIssuedAt(DateUtils.convertLocalDateTimeToDate(now))
                .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .compressWith(CompressionCodecs.GZIP)
                .signWith(SignatureAlgorithm.HS512, jwtConfiguration.getSecretKeySpec())
                .compact()
    }

    fun getTokenClaims(httpServletRequest: HttpServletRequest): Claims {
        return jwtParser.getClaims(getTokenFromHeader(httpServletRequest))
    }

    private fun getTokenFromHeader(httpServletRequest: HttpServletRequest): String {
        val authenticationHeader = httpServletRequest.getHeader("Authorization")
        val startWithBearer = StringUtils.startsWith(authenticationHeader,"Bearer")
        val headerParams = StringUtils.split(authenticationHeader, StringUtils.SPACE)
        val headerParamSizeIsTwo = ArrayUtils.getLength(headerParams) == 2
        val isAuthenticationHeaderValid = authenticationHeader != null && startWithBearer && headerParamSizeIsTwo

        if (!isAuthenticationHeaderValid) {
            println("Not valid!")
        }

        return authenticationHeader!!.split(StringUtils.SPACE)[1]
    }



}