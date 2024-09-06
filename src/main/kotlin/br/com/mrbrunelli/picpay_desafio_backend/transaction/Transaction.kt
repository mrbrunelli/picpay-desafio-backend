package br.com.mrbrunelli.picpay_desafio_backend.transaction

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Table("TRANSACTIONS")
data class Transaction(
    @Id val id: Long?,
    val payer: Long,
    val payee: Long,
    val value: BigDecimal,
    @CreatedDate val createdAt: LocalDateTime?
) {
    init {
        value.setScale(2)
    }
}