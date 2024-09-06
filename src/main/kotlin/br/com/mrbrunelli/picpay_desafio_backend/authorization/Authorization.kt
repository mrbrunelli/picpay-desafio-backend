package br.com.mrbrunelli.picpay_desafio_backend.authorization

data class Authorization(
    val message: String
) {
    fun isAuthorized(): Boolean {
        return message == "Autorizado"
    }
}
