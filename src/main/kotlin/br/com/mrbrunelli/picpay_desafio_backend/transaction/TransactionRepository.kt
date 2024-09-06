package br.com.mrbrunelli.picpay_desafio_backend.transaction

import org.springframework.data.repository.ListCrudRepository

interface TransactionRepository : ListCrudRepository<Transaction, Long>  {
}