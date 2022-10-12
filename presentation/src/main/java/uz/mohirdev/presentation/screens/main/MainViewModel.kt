package uz.mohirdev.presentation.screens.main

import com.github.terrakok.cicerone.Router
import uz.mohirdev.domain.usecase.settings.GetOnboardedUseCase
import uz.mohirdev.presentation.base.BaseViewModel
import uz.mohirdev.presentation.navigation.Screens.OnboardingScreen
import uz.mohirdev.presentation.navigation.Screens.PhoneScreen
import uz.mohirdev.presentation.screens.main.MainViewModel.*

class MainViewModel(
    private val getOnboardedUseCase: GetOnboardedUseCase,
    private val router: Router
) : BaseViewModel<State, Input, Effect>() {

    class State

    class Input

    class Effect

    init {
        getOnboarded()
    }

    override fun getDefaultState() = State()

    override fun processInput(input: Input) {

    }

    private fun getOnboarded() {
        getOnboardedUseCase().subscribe { onboarded ->
            router.newRootScreen(
                if (onboarded) PhoneScreen() else OnboardingScreen()
            )
        }
    }
}