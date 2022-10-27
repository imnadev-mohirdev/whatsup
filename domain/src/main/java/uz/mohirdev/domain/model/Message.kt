package uz.mohirdev.domain.model

import android.net.Uri
import java.util.*

data class Message(
    var id: String,
    var message: String? = null,
    var time: Date,
    var type: Type,
    var imageUri: Uri? = null
)

enum class Type {
    text_in, text_out, image_upload
}
