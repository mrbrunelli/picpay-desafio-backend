package br.com.mrbrunelli.picpay_desafio_backend.notification

import br.com.mrbrunelli.picpay_desafio_backend.transaction.Transaction
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class NotificationConsumer(
    private val builder: RestClient.Builder
) {
    private val restClient = builder.baseUrl("https://util.devi.tools/api/v1/notify").build()
    private val logger = LoggerFactory.getLogger(NotificationConsumer::class.java)

    @KafkaListener(topics = ["transaction-notification"], groupId = "picpay-desafio-backend")
    fun consume(transaction: Transaction) {
        logger.info("Notifying transaction: $transaction")

        val response = restClient.get()
            .retrieve()
            .toEntity(Notification::class.java)

        if (response.statusCode.isError || !response.body?.isStatusOk()!!) {
            throw NotificationException("Error sending notification")
        }

        logger.info("Notification has been sent: ${response.body}")
    }
}