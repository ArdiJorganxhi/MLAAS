package dev.ardijorganxhi.mlaas.model.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class LoginRequest(
    @NotBlank(message = "Email cannot be empty.")
    @Email(message = "Not an valid email type.")
    var email: String,

    @NotBlank(message = "Password cannot be empty.")
    var password: String
)
