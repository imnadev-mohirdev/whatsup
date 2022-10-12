package uz.mohirdev.presentation.screens.onboarding

import com.github.terrakok.cicerone.Router
import uz.mohirdev.domain.usecase.settings.OnboardedUseCase
import uz.mohirdev.presentation.base.BaseViewModel
import uz.mohirdev.presentation.navigation.Screens.PhoneScreen
import uz.mohirdev.presentation.screens.onboarding.OnboardingViewModel.*

class OnboardingViewModel(
    private val onboardedUseCase: OnboardedUseCase,
    private val router: Router
) : BaseViewModel<State, Input, Effect>() {

    class State

    sealed class Input {
        object Onboarded : Input()
    }

    class Effect

    override fun getDefaultState() = State()

    override fun processInput(input: Input) {
        when (input) {
            Input.Onboarded -> onboarded()
        }
    }

    private fun onboarded() {
        onboardedUseCase().subscribe {
            router.replaceScreen(PhoneScreen())
        }
    }
}