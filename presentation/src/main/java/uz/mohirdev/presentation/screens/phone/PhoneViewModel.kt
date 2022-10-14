package uz.mohirdev.presentation.screens.phone

import com.github.terrakok.cicerone.Router
import uz.mohirdev.domain.usecase.auth.SendSmsCodeUseCase
import uz.mohirdev.presentation.base.BaseViewModel
import uz.mohirdev.presentation.navigation.Screens.CodeScreen
import uz.mohirdev.presentation.screens.phone.PhoneViewModel.*

class PhoneViewModel constructor(
    private val sendSmsCodeUseCase: SendSmsCodeUseCase,
    private val router: Router
) : BaseViewModel<State, Input, Effect>() {

    data class State(
        val loading: Boolean = false
    )

    sealed class Effect {
        object Error : Effect()
    }

    sealed class Input {
        data class SendCode(val phone: String) : Input()
    }

    override fun getDefaultState() = State()

    override fun processInput(input: Input) {
        when (input) {
            is Input.SendCode -> sendCode(input.phone)
        }
    }

    private fun sendCode(phone: String) = sendSmsCodeUseCase(phone)
        .doOnSubscribe {
            updateState { it.copy(loading = true) }
        }.doOnError {
            emitEffect(Effect.Error)
        }.doOnComplete {
            router.navigateTo(CodeScreen(phone))
        }.doFinally {
            updateState { it.copy(loading = false) }
        }.subscribe()
}