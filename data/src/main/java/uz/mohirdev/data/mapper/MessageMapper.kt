package uz.mohirdev.data.mapper

import android.net.Uri
import uz.mohirdev.data.remote.messages.MessageDocument
import uz.mohirdev.domain.model.Message
import uz.mohirdev.domain.model.Type

fun MessageDocument.toMessage(userId: String): Message? {
    return Message(
        id = id ?: return null,
        message = message,
        time = time ?: return null,
        type = when (from) {
            userId -> when {
                image != null -> Type.image_out
                else -> Type.text_out
            }
            else -> when {
                image != null -> Type.image_in
                else -> Type.text_in
            }
        },
        image = image?.let { Uri.parse(it) }
    )
}