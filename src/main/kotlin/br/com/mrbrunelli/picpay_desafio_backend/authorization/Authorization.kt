package br.com.mrbrunelli.picpay_desafio_backend.authorization


data class Authorization(
    val status: String,
    val data: AuthorizationData
) {
    fun isAuthorized(): Boolean {
        return data.authorization
    }
}

data class AuthorizationData(
    val authorization: Boolean
)