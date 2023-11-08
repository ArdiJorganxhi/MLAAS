package dev.ardijorganxhi.mlaas.service

import dev.ardijorganxhi.mlaas.entity.User
import dev.ardijorganxhi.mlaas.mapper.AuthMapper
import dev.ardijorganxhi.mlaas.mapper.IdentityUserMapper
import dev.ardijorganxhi.mlaas.mapper.UserMapper
import dev.ardijorganxhi.mlaas.model.UserAuthentication
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
    private val authMapper: AuthMapper,
    private val tokenService: TokenService,
    private val identityUserMapper: IdentityUserMapper,
    private val authenticationManager: AuthenticationManager,
    private val userMapper: UserMapper,
    private val passwordEncoder: BCryptPasswordEncoder) {

    @Transactional
    fun register(registerRequest: RegisterRequest) {
        registerRequest.password = passwordEncoder.encode(registerRequest.password)
        val user = authMapper.register(registerRequest)
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
        val user = userRepository.findByEmail(request.email) as User
        return tokenService.createToken(userMapper.convertToDto(user))
    }

    fun getUserAuthentication(httpServletRequest: HttpServletRequest): Authentication {
        val claims: Claims = tokenService.getTokenClaims(httpServletRequest)
        return UserAuthentication(identityUserMapper.getUser(claims))
    }

}