package uz.mohirdev.data.mapper

import uz.mohirdev.data.remote.messages.MessageDocument
import uz.mohirdev.domain.model.Message
import uz.mohirdev.domain.model.Type

fun MessageDocument.toMessage(userId: String) : Message? {
    return Message(
        id = id ?: return null,
        message = message ?: return null,
        time = time ?: return null,
        type = if (from == userId) Type.text_out else Type.text_in
    )
}