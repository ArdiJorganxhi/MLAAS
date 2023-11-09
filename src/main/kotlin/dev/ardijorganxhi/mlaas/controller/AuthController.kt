package dev.ardijorganxhi.mlaas.controller

import dev.ardijorganxhi.mlaas.model.request.LoginRequest
import dev.ardijorganxhi.mlaas.model.request.RegisterRequest
import dev.ardijorganxhi.mlaas.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import kotlin.math.log

@RestController
@RequestMapping("/api/auth")
class AuthController(private val authService: AuthService) {

    @PostMapping("/register")
    fun register(@Valid @RequestBody registerRequest: RegisterRequest): ResponseEntity<String> {
        authService.register(registerRequest)
        return ResponseEntity.ok("User is registered!")
    }

    @PostMapping("/login")
    fun login(@Valid @RequestBody loginRequest: LoginRequest): ResponseEntity<String> {
        return ResponseEntity.ok(authService.login(request = loginRequest))
    }
}