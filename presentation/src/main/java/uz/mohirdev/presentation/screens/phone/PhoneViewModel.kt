package uz.mohirdev.presentation.screens.phone

import uz.mohirdev.domain.model.User
import uz.mohirdev.domain.usecase.auth.SendSmsCodeUseCase
import uz.mohirdev.presentation.base.BaseViewModel
import uz.mohirdev.presentation.screens.phone.PhoneViewModel.*

class PhoneViewModel constructor(
    private val sendSmsCodeUseCase: SendSmsCodeUseCase
) : BaseViewModel<State, Input, Effect>() {

    data class State(
        val user: User? = null
    )

    class Effect

    class Input

    override fun getDefaultState() = State()

    override fun processInput(input: Input) { }
}