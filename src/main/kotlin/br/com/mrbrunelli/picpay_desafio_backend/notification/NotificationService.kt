package br.com.mrbrunelli.picpay_desafio_backend.notification

import br.com.mrbrunelli.picpay_desafio_backend.transaction.Transaction
import org.springframework.stereotype.Service

@Service
class NotificationService(private val notificationProducer: NotificationProducer) {
    fun notify(transaction: Transaction) {
        notificationProducer.send(transaction)
    }
}