package dev.ardijorganxhi.mlaas.client

import org.springframework.cloud.openfeign.FeignClient


@FeignClient(value = "huggingfaceclient")
interface HuggingFaceClient {
}