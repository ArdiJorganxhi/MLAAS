package dev.ardijorganxhi.mlaas.exception

import dev.ardijorganxhi.mlaas.model.error.ErrorResponse
import org.springframework.http.HttpStatus


class AuthException(private val errorResponse: ErrorResponse) : RuntimeException() {
    private val httpStatus: HttpStatus = HttpStatus.UNAUTHORIZED

}