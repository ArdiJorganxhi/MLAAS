package dev.ardijorganxhi.mlaas.filter

import dev.ardijorganxhi.mlaas.service.AuthService
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.MalformedJwtException
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException
import java.security.SignatureException
import javax.security.auth.message.AuthException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JwtAuthenticationFilter(private val authService: AuthService) : GenericFilterBean() {

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, filterChain: FilterChain) {
        val httpRequest = servletRequest as HttpServletRequest?
        try {
            val userAuthentication: Authentication = authService.getUserAuthentication(httpRequest!!)
            SecurityContextHolder.getContext().authentication = userAuthentication
        } catch (e: AuthException) {
            handleSecurityError(servletResponse as HttpServletResponse, "Authentication header not valid.")
            return
        } catch (m: MalformedJwtException) {
            handleSecurityError(servletResponse as HttpServletResponse, "JWT was not correctly constructed.")
            return
        } catch (e: ExpiredJwtException) {
            handleSecurityError(servletResponse as HttpServletResponse, "Token is expired.")
            return
        } catch (e: SignatureException) {
            handleSecurityError(servletResponse as HttpServletResponse, "Token is not valid.")
            return
        }
        filterChain.doFilter(servletRequest, servletResponse)
    }

    @Throws(IOException::class)
    private fun handleSecurityError(httpServletResponse: HttpServletResponse, message: String) {
        SecurityContextHolder.clearContext()
        httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), message)
    }

}