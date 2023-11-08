package dev.ardijorganxhi.mlaas.model

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import java.io.Serial
import java.io.Serializable
import java.util.Collections

data class UserAuthentication(
    private val identityUser: IdentityUser
) : Authentication {

    @Serial
    private val serialVersionUID = -1L

    private var authenticated = true

    override fun getName(): String? {
        return null
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return Collections.emptyList()
    }

    override fun getCredentials(): Any? {
        return null
    }

    override fun getDetails(): IdentityUser {
        return identityUser
    }

    override fun getPrincipal(): Any {
        return identityUser.id
    }

    override fun isAuthenticated(): Boolean {
        return authenticated
    }


    override fun setAuthenticated(isAuthenticated: Boolean) {
        authenticated = isAuthenticated
    }


}
