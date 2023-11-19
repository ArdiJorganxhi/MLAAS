package dev.ardijorganxhi.mlaas.model.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class TextClassificationResponse(
    val label: String,
    val score: String
)
