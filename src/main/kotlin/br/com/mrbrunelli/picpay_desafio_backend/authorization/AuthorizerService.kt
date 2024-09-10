package br.com.mrbrunelli.picpay_desafio_backend.authorization

import br.com.mrbrunelli.picpay_desafio_backend.transaction.Transaction
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class AuthorizerService(
    private val builder: RestClient.Builder
) {
    private val restClient: RestClient = builder.baseUrl("https://util.devi.tools/api/v2/authorize").build()
    private val logger = LoggerFactory.getLogger(AuthorizerService::class.java)

    fun authorize(transaction: Transaction) {
        logger.info("Authorizing transaction: $transaction")

        val response = restClient.get()
            .retrieve()
            .toEntity(Authorization::class.java)

        if (response.statusCode.isError || !response.body?.isAuthorized()!!) {
            throw UnauthorizedTransactionException("Unauthorized transaction")
        }

        logger.info("Transaction authorized: $transaction")
    }
}