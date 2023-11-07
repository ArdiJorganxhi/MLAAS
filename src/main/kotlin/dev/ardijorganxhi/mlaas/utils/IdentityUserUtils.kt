package dev.ardijorganxhi.mlaas.utils

import dev.ardijorganxhi.mlaas.model.IdentityUser
import dev.ardijorganxhi.mlaas.model.UserAuthentication
import org.springframework.security.core.context.SecurityContextHolder

object IdentityUserUtils {
    fun getUser(): IdentityUser {
        val userAuthentication = SecurityContextHolder.getContext().authentication as UserAuthentication
        return userAuthentication.details
    }

    fun getId(): Int {
        return getUser().id
    }
}
