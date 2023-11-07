package dev.ardijorganxhi.mlaas.model.request

import dev.ardijorganxhi.mlaas.entity.User

data class RegisterRequest(var name: String, var surname: String, var email: String, var password: String)