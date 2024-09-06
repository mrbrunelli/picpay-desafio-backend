package br.com.mrbrunelli.picpay_desafio_backend.wallet

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal

@Table("WALLETS")
data class Wallet(
    @Id val id: Long?,
    val fullName: String,
    val cpf: Long,
    val email: String,
    val password: String,
    val type: Int,
    val balance: BigDecimal
) {
    fun debit(value: BigDecimal): Wallet {
        return Wallet(id, fullName, cpf, email, password, type, balance.subtract(value))
    }
}