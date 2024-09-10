package br.com.mrbrunelli.picpay_desafio_backend.transaction

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class TransactionExceptionHandler {
    @ExceptionHandler(InvalidTransactionException::class)
    fun handle(e: InvalidTransactionException): ResponseEntity<Any> {
        return ResponseEntity.badRequest().body(e.message)
    }
}