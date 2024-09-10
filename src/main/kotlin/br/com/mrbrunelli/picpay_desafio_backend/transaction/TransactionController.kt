package br.com.mrbrunelli.picpay_desafio_backend.transaction

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transactions")
class TransactionController(private val transactionService: TransactionService) {
    @PostMapping
    fun create(@RequestBody transaction: Transaction): Transaction {
        return transactionService.create(transaction)
    }

    @GetMapping
    fun list(): List<Transaction> {
        return transactionService.list()
    }
}