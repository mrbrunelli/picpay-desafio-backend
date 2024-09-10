package br.com.mrbrunelli.picpay_desafio_backend.notification

data class Notification(
    val data: NotificationData
) {
    fun isStatusOk(): Boolean {
        return data.status == "success"
    }
}

data class NotificationData(
    val status: String,
    val message: String
)
