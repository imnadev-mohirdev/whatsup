package uz.mohirdev.data.repo

import uz.mohirdev.data.remote.auth.AuthFirebase
import uz.mohirdev.domain.repo.AuthRepository

class AuthRepositoryImpl constructor(
    private val authFirebase: AuthFirebase
) : AuthRepository {

    override fun sendSmsCode(phone: String) {
        authFirebase.sendSmsCode(phone)
        TODO("Not yet implemented")
    }
}