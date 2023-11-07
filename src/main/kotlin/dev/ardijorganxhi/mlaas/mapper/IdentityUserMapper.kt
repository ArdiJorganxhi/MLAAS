package dev.ardijorganxhi.mlaas.mapper

import dev.ardijorganxhi.mlaas.model.IdentityUser
import io.jsonwebtoken.Claims
import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Component
import java.util.*


@Component
class IdentityUserMapper {

    fun getUser(claims: Claims): IdentityUser {
        return IdentityUser(
            id = (Integer.valueOf(Objects.requireNonNull(getStringValue(claims, "id")))),
            email = (getStringValue(claims, "email"))
        )
    }

    private fun getStringValue(claims: Claims, key: String): String {
        val foundValue = claims.getOrDefault(key, StringUtils.EMPTY)
        return foundValue.toString()
    }
}