package uz.mohirdev.domain.usecase.settings

import uz.mohirdev.domain.repo.SettingsRepository

class OnboardedUseCase(
    private val settingsRepository: SettingsRepository
) {

    operator fun invoke() = settingsRepository.onboarded()
}