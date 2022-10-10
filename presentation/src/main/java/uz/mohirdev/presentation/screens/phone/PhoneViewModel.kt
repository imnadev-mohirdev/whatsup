package uz.mohirdev.presentation.screens.phone

import androidx.lifecycle.ViewModel
import uz.mohirdev.domain.usecase.auth.SendSmsCodeUseCase

class PhoneViewModel constructor(
    private val sendSmsCodeUseCase: SendSmsCodeUseCase
) : ViewModel() {

}