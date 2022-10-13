package uz.mohirdev.domain.usecase.auth

import uz.mohirdev.domain.repo.AuthRepository

class VerifyCodeUseCase(
    private val authRepository: AuthRepository
) {
    operator fun invoke(code: String) = authRepository.verify(code)
}