package uz.mohirdev.domain.usecase.auth

import uz.mohirdev.domain.repo.AuthRepository

class SendSmsCodeUseCase(
    private val authRepository: AuthRepository
) {

    operator fun invoke(phone: String) {
        authRepository.sendSmsCode(phone)
    }
}