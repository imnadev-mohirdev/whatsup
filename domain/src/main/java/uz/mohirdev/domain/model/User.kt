package uz.mohirdev.domain.model

data class User(
    var id: String,
    val phone: String,
    val name: String,
    val avatar: String?
)
