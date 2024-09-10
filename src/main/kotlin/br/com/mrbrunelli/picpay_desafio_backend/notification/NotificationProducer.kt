package br.com.mrbrunelli.picpay_desafio_backend.notification

import br.com.mrbrunelli.picpay_desafio_backend.transaction.Transaction
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class NotificationProducer(
    private val kafkaTemplate: KafkaTemplate<String, Transaction>
) {

    fun send(transaction: Transaction) {
        kafkaTemplate.send("transaction-notification", transaction)
    }
}