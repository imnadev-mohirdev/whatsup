package uz.mohirdev.data.remote.messages

import android.net.Uri
import com.google.firebase.firestore.ServerTimestamp
import java.util.*

data class MessageDocument(
    var id: String? = null,
    var message: String? = null,
    @ServerTimestamp
    var time: Date? = null,
    var members: String? = null,
    val from: String? = null,
    val image: String? = null
)
