package dev.ardijorganxhi.mlaas.service

import dev.ardijorganxhi.mlaas.entity.User
import dev.ardijorganxhi.mlaas.exception.ApiException
import dev.ardijorganxhi.mlaas.mapper.*
import dev.ardijorganxhi.mlaas.model.UserAuthentication
import dev.ardijorganxhi.mlaas.model.error.ErrorEnum
import dev.ardijorganxhi.mlaas.model.request.LoginRequest
import dev.ardijorganxhi.mlaas.model.request.RegisterRequest
import dev.ardijorganxhi.mlaas.repository.UserRepository
import io.jsonwebtoken.Claims
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletRequest
import javax.transaction.Transactional


@Service
class AuthService(
    private val userRepository: UserRepository,
    private val tokenService: TokenService,
    private val authenticationManager: AuthenticationManager,
    private val passwordEncoder: BCryptPasswordEncoder) {

    @Transactional
    fun register(registerRequest: RegisterRequest) {
        val user = User.Builder()
                .name(registerRequest.name)
                .surname(registerRequest.surname)
                .email(registerRequest.email)
                .pass(passwordEncoder.encode(registerRequest.password))
                .build()

        userRepository.save(user)
    }

    @Throws(Exception::class)
    fun login(request: LoginRequest): String? {
        try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(request.email, request.password))
        } catch (e: DisabledException) {
            throw Exception("DisabledException")
        } catch (e: BadCredentialsException) {
            throw Exception("BadCredentialsException")
        }
        val user = userRepository.findByEmail(request.email)
        return if(user != null) {
            tokenService.createToken(user.convertToDto())
        } else {
            throw ApiException(ErrorEnum.UNAUTHORIZED)
        }

    }

    fun getUserAuthentication(httpServletRequest: HttpServletRequest): Authentication {
        val claims: Claims = tokenService.getTokenClaims(httpServletRequest)
        return UserAuthentication(claims.getUser())
    }

}