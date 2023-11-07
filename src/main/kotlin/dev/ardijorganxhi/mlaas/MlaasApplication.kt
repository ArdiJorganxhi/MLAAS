package dev.ardijorganxhi.mlaas

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class MlaasApplication

fun main(args: Array<String>) {
	runApplication<MlaasApplication>(*args)
}
