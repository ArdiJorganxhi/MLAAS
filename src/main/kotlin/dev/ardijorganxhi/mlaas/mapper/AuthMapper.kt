package dev.ardijorganxhi.mlaas.mapper

import dev.ardijorganxhi.mlaas.entity.User
import dev.ardijorganxhi.mlaas.model.request.RegisterRequest
import org.springframework.stereotype.Component

@Component
class AuthMapper {

    fun register(registerRequest: RegisterRequest): User {
        return User(
            name = registerRequest.name,
            surname = registerRequest.surname,
            email = registerRequest.email,
            pass = registerRequest.password
        )
    }
}

