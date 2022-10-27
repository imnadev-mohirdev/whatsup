package uz.mohirdev.presentation.screens.code

import com.github.terrakok.cicerone.Router
import uz.mohirdev.domain.usecase.auth.VerifyCodeUseCase
import uz.mohirdev.presentation.base.BaseViewModel
import uz.mohirdev.presentation.navigation.Screens.HomeScreen
import uz.mohirdev.presentation.screens.code.CodeViewModel.*

class CodeViewModel(
    private val verifyCodeUseCase: VerifyCodeUseCase,
    private val router: Router
) : BaseViewModel<State, Input, Effect>() {

    class State

    sealed class Input {
        data class Verify(val code: String) : Input()
    }

    sealed class Effect {
        object Error : Effect()
    }

    override fun getDefaultState() = State()

    override fun processInput(input: Input) {
        when (input) {
            is Input.Verify -> verify(input.code)
        }
    }

    private fun verify(code: String) = verifyCodeUseCase(code)
        .doOnError {
            emitEffect(Effect.Error)
        }.doOnComplete {
            router.replaceScreen(HomeScreen())
        }.subscribe({}, {})
}