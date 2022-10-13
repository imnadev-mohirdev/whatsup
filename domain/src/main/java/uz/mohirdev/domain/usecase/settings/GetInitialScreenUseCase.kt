package uz.mohirdev.domain.usecase.settings

import io.reactivex.rxjava3.core.Single
import uz.mohirdev.domain.repo.AuthRepository
import uz.mohirdev.domain.repo.SettingsRepository

class GetInitialScreenUseCase(
    private val settingsRepository: SettingsRepository,
    private val authRepository: AuthRepository
) {

    operator fun invoke(): Single<Result> = settingsRepository.getOnboarded().map { onboarded ->
        return@map when {
            authRepository.isLoggedIn -> Result.Home
            onboarded -> Result.Phone
            else -> Result.Onboarding
        }
    }

    sealed class Result {
        object Onboarding : Result()
        object Phone : Result()
        object Home : Result()
    }
}