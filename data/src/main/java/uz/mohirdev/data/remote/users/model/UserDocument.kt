package uz.mohirdev.data.remote.users.model

data class UserDocument(
    var id: String? = null,
    val phone: String? = null,
    val name: String? = null,
    val avatar: String? = null
)