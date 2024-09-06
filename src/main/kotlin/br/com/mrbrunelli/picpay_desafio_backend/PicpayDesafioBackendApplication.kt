package br.com.mrbrunelli.picpay_desafio_backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing

@EnableJdbcAuditing
@SpringBootApplication
class PicpayDesafioBackendApplication

fun main(args: Array<String>) {
	runApplication<PicpayDesafioBackendApplication>(*args)
}
