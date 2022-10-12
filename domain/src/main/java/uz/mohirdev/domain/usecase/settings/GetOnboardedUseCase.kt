package uz.mohirdev.domain.usecase.settings

import uz.mohirdev.domain.repo.SettingsRepository

class GetOnboardedUseCase(
    private val settingsRepository: SettingsRepository
) {

    operator fun invoke() = settingsRepository.getOnboarded()
}