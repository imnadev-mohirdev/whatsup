package uz.mohirdev.presentation.screens.main

import com.github.terrakok.cicerone.Router
import uz.mohirdev.domain.usecase.settings.GetInitialScreenUseCase
import uz.mohirdev.domain.usecase.settings.GetInitialScreenUseCase.Result
import uz.mohirdev.presentation.base.BaseViewModel
import uz.mohirdev.presentation.navigation.Screens.HomeScreen
import uz.mohirdev.presentation.navigation.Screens.OnboardingScreen
import uz.mohirdev.presentation.navigation.Screens.PhoneScreen
import uz.mohirdev.presentation.screens.main.MainViewModel.*

class MainViewModel(
    private val getInitialScreenUseCase: GetInitialScreenUseCase,
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
        getInitialScreenUseCase().subscribe { result ->
            val screen = when(result) {
                Result.Home -> HomeScreen()
                Result.Onboarding -> OnboardingScreen()
                Result.Phone -> PhoneScreen()
            }
            router.replaceScreen(screen)
        }
    }
}