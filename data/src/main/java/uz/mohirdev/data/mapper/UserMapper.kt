package uz.mohirdev.data.mapper

import uz.mohirdev.data.remote.users.model.UserDocument
import uz.mohirdev.domain.model.User

fun UserDocument.toUser(): User? {
    return User(
        id = id ?: return null,
        phone = phone ?: return null,
        name = name ?: return null,
        avatar = avatar
    )
}