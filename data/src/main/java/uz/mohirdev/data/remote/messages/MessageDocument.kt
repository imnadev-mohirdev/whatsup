package uz.mohirdev.data.remote.messages

import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class MessageDocument(
    var id: String? = null,
    var message: String? = null,
    @ServerTimestamp
    var time: Date? = null,
    var members: List<String>? = null,
    var count: Int? = null,
    val from: String? = null
)
