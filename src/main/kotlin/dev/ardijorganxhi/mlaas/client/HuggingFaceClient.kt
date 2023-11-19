package dev.ardijorganxhi.mlaas.client

import dev.ardijorganxhi.mlaas.model.FeignResponse
import dev.ardijorganxhi.mlaas.model.response.TextClassificationResponse
import dev.ardijorganxhi.mlaas.model.response.TextGenerationResponse
import dev.ardijorganxhi.mlaas.model.response.TextSummarizationResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader


@FeignClient(value = "huggingfaceclient", url = "\${huggingface.base.url}")
interface HuggingFaceClient {

    @PostMapping("/facebook/bart-large-cnn")
    fun textSummarization(@RequestBody inputs: String, @RequestHeader("Authorization") token: String) : List<TextSummarizationResponse>

    @PostMapping("/distilbert-base-uncased-finetuned-sst-2-english")
    fun textClassification(@RequestBody inputs: String, @RequestHeader("Authorization") token: String): List<List<TextClassificationResponse>>

    @PostMapping("/gpt2")
    fun textGeneration(@RequestBody inputs: String, @RequestHeader("Authorization") token: String): List<TextGenerationResponse>
}