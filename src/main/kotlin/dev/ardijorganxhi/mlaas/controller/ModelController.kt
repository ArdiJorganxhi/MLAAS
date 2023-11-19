package dev.ardijorganxhi.mlaas.controller

import dev.ardijorganxhi.mlaas.model.FeignResponse
import dev.ardijorganxhi.mlaas.model.response.TextClassificationResponse
import dev.ardijorganxhi.mlaas.model.response.TextGenerationResponse
import dev.ardijorganxhi.mlaas.model.response.TextSummarizationResponse
import dev.ardijorganxhi.mlaas.service.TextService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/models")
class ModelController(private val textService: TextService) {

    @PostMapping("/texts/summarize")
    fun summarizeText(@RequestBody inputs: String): ResponseEntity<FeignResponse<TextSummarizationResponse>> {
        return ResponseEntity.ok(textService.summarizeText(inputs))
    }

    @PostMapping("/texts/classify")
    fun classifyText(@RequestBody inputs: String): ResponseEntity<FeignResponse<List<TextClassificationResponse>>> {
        return ResponseEntity.ok(textService.classifyText(inputs))
    }

    @PostMapping("/texts/generate")
    fun generateText(@RequestBody inputs: String): ResponseEntity<FeignResponse<TextGenerationResponse>> {
        return ResponseEntity.ok(textService.generateText(inputs))
    }
}