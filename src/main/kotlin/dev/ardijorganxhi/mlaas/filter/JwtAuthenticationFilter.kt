package dev.ardijorganxhi.mlaas.filter

import dev.ardijorganxhi.mlaas.service.AuthService
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.MalformedJwtException
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.security.SignatureException
import javax.security.auth.message.AuthException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JwtAuthenticationFilter(private val authService: AuthService) : OncePerRequestFilter() {

    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(servletRequest: HttpServletRequest, servletResponse: HttpServletResponse, filterChain: FilterChain) {
            try {
                val userAuthentication: Authentication = authService.getUserAuthentication(servletRequest)
                SecurityContextHolder.getContext().authentication = userAuthentication
            } catch (m: MalformedJwtException) {
                handleSecurityError(servletResponse, "JWT was not correctly constructed.")
                return
            } catch (e: ExpiredJwtException) {
                handleSecurityError(servletResponse, "Token is expired.")
                return
            } catch (e: SignatureException) {
                handleSecurityError(servletResponse, "Token is not valid.")
                return
            }
            filterChain.doFilter(servletRequest, servletResponse)
        }
    }


    @Throws(IOException::class)
    private fun handleSecurityError(httpServletResponse: HttpServletResponse, message: String) {
        SecurityContextHolder.clearContext()
        httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), message)
    }

