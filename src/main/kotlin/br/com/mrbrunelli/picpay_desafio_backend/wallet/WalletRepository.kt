package br.com.mrbrunelli.picpay_desafio_backend.wallet

import org.springframework.data.repository.CrudRepository

interface WalletRepository : CrudRepository<Wallet, Long> {
}