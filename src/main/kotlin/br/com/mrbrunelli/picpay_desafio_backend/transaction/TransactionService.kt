package br.com.mrbrunelli.picpay_desafio_backend.transaction

import br.com.mrbrunelli.picpay_desafio_backend.authorization.AuthorizerService
import br.com.mrbrunelli.picpay_desafio_backend.notification.NotificationService
import br.com.mrbrunelli.picpay_desafio_backend.wallet.Wallet
import br.com.mrbrunelli.picpay_desafio_backend.wallet.WalletRepository
import br.com.mrbrunelli.picpay_desafio_backend.wallet.WalletType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TransactionService(
    private val transactionRepository: TransactionRepository,
    private val walletRepository: WalletRepository,
    private val authorizerService: AuthorizerService,
    private val notificationService: NotificationService
) {
    @Transactional
    fun create(transaction: Transaction): Transaction {
        validate(transaction)

        val newTransaction = transactionRepository.save(transaction)

        val wallet = walletRepository.findById(transaction.payer).get()
        walletRepository.save(wallet.debit(transaction.value))

        authorizerService.authorize(transaction)

        notificationService.notify(transaction)

        return newTransaction
    }

    private fun validate(transaction: Transaction) {
        walletRepository.findById(transaction.payee)
            .orElseThrow {
                InvalidTransactionException("Invalid transaction - $transaction")
            }

        val payer = walletRepository.findById(transaction.payer)
            .orElseThrow {
                InvalidTransactionException("Invalid transaction - $transaction")
            }

        if (!isTransactionValid(transaction, payer)) {
            throw InvalidTransactionException("Invalid transaction - $transaction")
        }
    }

    private fun isTransactionValid(transaction: Transaction, payer: Wallet): Boolean {
        return payer.type == WalletType.COMUM.value &&
                payer.balance >= transaction.value &&
                payer.id != transaction.payee
    }
}