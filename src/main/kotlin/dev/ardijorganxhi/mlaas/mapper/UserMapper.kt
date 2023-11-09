package dev.ardijorganxhi.mlaas.mapper

import dev.ardijorganxhi.mlaas.entity.User
import dev.ardijorganxhi.mlaas.model.dto.UserDto
import org.springframework.stereotype.Component

@Component
class UserMapper {

    fun convertToDto(user: User) : UserDto {
        return UserDto(
            id = user.id,
            name = user.name!!,
            surname = user.surname!!,
            email = user.email!!
        )
    }
}