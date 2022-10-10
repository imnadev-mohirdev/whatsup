package uz.mohirdev.domain.repo

interface AuthRepository {
    fun sendSmsCode(phone: String)
}