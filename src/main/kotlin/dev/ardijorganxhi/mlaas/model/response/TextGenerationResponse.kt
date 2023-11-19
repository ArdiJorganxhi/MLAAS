package dev.ardijorganxhi.mlaas.model.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class TextGenerationResponse(
    @JsonProperty("generated_text")
    val generatedText: String?
)
