package dev.ardijorganxhi.mlaas.config

import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ResourceBundleMessageSource


@Configuration
class MessageSourceConfiguration {
    @Bean
    fun messageSource(): MessageSource {
        val messageSource = ResourceBundleMessageSource()
        messageSource.setBasenames("messages/messages")
        messageSource.setDefaultEncoding("UTF-8")
        return messageSource
    }
}