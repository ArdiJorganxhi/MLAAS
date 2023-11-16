package dev.ardijorganxhi.mlaas.mapper

import dev.ardijorganxhi.mlaas.model.IdentityUser
import io.jsonwebtoken.Claims
import org.apache.commons.lang3.StringUtils
import java.util.*


fun Claims.getUser(): IdentityUser {
    return IdentityUser(
        id = (Integer.valueOf(Objects.requireNonNull(getStringValue(this, "id")))),
        email = (getStringValue(this, "email"))
    )
}

private fun getStringValue(claims: Claims, key: String): String {
    val foundValue = claims.getOrDefault(key, StringUtils.EMPTY)
    return foundValue.toString()
}