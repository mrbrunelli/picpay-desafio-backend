package br.com.mrbrunelli.picpay_desafio_backend

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing
import org.springframework.kafka.config.TopicBuilder

@EnableJdbcAuditing
@SpringBootApplication
class PicpayDesafioBackendApplication

fun main(args: Array<String>) {
	runApplication<PicpayDesafioBackendApplication>(*args)
}

@Bean
fun notificationTopic(): NewTopic {
	return TopicBuilder.name("transaction-notification").build()
}
