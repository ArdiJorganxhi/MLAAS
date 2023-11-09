package dev.ardijorganxhi.mlaas.model.error

import org.springframework.http.HttpStatus

enum class ErrorEnum(
    var code: Int,
    var key: String,
    var httpStatus: HttpStatus
) {

    UNEXPECTED_ERROR(1000, "An unexpected error occured.", HttpStatus.INTERNAL_SERVER_ERROR),
    VALIDATION_ERROR(1001, "An validation error occured.", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(1002, "Unauthorized user.", HttpStatus.UNAUTHORIZED),
    USER_NOT_FOUND(1003, "User not found.", HttpStatus.NOT_FOUND),
    EMAIL_ALREADY_EXIST(1004, "Email already exists.", HttpStatus.BAD_REQUEST),
    USER_NOT_CREATED(1005, "User not created.", HttpStatus.INTERNAL_SERVER_ERROR);
}