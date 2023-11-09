package dev.ardijorganxhi.mlaas.exception

import dev.ardijorganxhi.mlaas.model.error.ErrorEnum


class ApiException : RuntimeException {
    private val error: ErrorEnum
    private val args: Array<String?>

    constructor(error: ErrorEnum) {
        this.error = error
        args = arrayOfNulls(0)
    }

    constructor(error: ErrorEnum, vararg args: String?) {
        this.error = error
        this.args = args as Array<String?>
    }
}
