package dev.ardijorganxhi.mlaas.service

import dev.ardijorganxhi.mlaas.client.HuggingFaceClient
import dev.ardijorganxhi.mlaas.model.FeignResponse
import dev.ardijorganxhi.mlaas.model.response.TextClassificationResponse
import dev.ardijorganxhi.mlaas.model.response.TextGenerationResponse
import dev.ardijorganxhi.mlaas.model.response.TextSummarizationResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class TextService(private val huggingFaceClient: HuggingFaceClient) {

    @Value("\${api.token}")
    private val token: String = ""

    fun summarizeText(inputs: String): FeignResponse<TextSummarizationResponse> {
        val response: List<TextSummarizationResponse> = huggingFaceClient.textSummarization(inputs, token)
        return FeignResponse(response)
    }

    fun classifyText(inputs: String): FeignResponse<List<TextClassificationResponse>> {
        val response: List<List<TextClassificationResponse>> = huggingFaceClient.textClassification(inputs, token)
        return FeignResponse(response)

    }

    fun generateText(inputs: String): FeignResponse<TextGenerationResponse> {
        val response: List<TextGenerationResponse> = huggingFaceClient.textGeneration(inputs, token)
        return FeignResponse(response)

    }
}