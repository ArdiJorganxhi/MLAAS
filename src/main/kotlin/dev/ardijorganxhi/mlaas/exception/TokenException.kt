package dev.ardijorganxhi.mlaas.exception


class TokenException(message: String) : RuntimeException("JWT Token could not generated, $message")