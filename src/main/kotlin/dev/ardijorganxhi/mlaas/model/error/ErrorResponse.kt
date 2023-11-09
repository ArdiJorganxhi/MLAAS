package dev.ardijorganxhi.mlaas.model.error

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime
import java.time.ZoneId

@JsonInclude(JsonInclude.Include.NON_NULL)
class ErrorResponse private constructor(
    var traceId: String?,
    var exception: String?,
    var timestamp: Long? = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
    var errors: List<ErrorDetail>?
) {
    data class Builder(
        var traceId: String? = null,
        var exception: String? = null,
        var timestamp: Long? = null,
        var errors: List<ErrorDetail>? = null
    ) {
        fun traceId(traceId: String) = apply { this.traceId = traceId }
        fun exception(exception: String) = apply { this.exception = exception }
        fun timestamp(timestamp: Long) = apply { this.timestamp = timestamp }
        fun errors(errors: List<ErrorDetail>) = apply { this.errors = errors }

        fun build() = ErrorResponse(traceId, exception, timestamp, errors)
    }
}
