package dev.ardijorganxhi.mlaas.model.response

import com.fasterxml.jackson.annotation.JsonProperty

data class TextSummarizationResponse(
    @JsonProperty("summary_text")
    val summaryText: String?
)
