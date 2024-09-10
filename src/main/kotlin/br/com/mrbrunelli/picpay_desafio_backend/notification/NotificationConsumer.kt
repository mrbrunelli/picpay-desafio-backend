package br.com.mrbrunelli.picpay_desafio_backend.notification

import br.com.mrbrunelli.picpay_desafio_backend.transaction.Transaction
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class NotificationConsumer(
    private val builder: RestClient.Builder
) {
    private val restClient = builder.baseUrl("https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6").build()

    @KafkaListener(topics = ["transaction-notification"], groupId = "picpay-desafio-backend")
    fun consume(transaction: Transaction) {
        val response = restClient.get()
            .retrieve()
            .toEntity(Notification::class.java)

        if (response.statusCode.isError || !response.body?.message!!) {
            throw NotificationException("Error sending notification")
        }
    }
}