package dev.ardijorganxhi.mlaas.model.request

import dev.ardijorganxhi.mlaas.entity.User
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class RegisterRequest(
    @NotBlank(message = "Name cannot be empty.")
    var name: String,

    @NotBlank(message = "Surname cannot be empty.")
    var surname: String,

    @NotBlank(message = "Email cannot be empty.")
    @Email(message = "Not an valid email type.")
    var email: String,

    @NotBlank(message = "Password cannot be empty.")
    var password: String
)