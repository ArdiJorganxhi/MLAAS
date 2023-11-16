package dev.ardijorganxhi.mlaas.mapper

import dev.ardijorganxhi.mlaas.entity.User
import dev.ardijorganxhi.mlaas.model.dto.UserDto


fun User.convertToDto(): UserDto {
    return UserDto(
        id = this.id,
        name = this.name!!,
        surname = this.surname!!,
        email = this.email!!
    )

}
