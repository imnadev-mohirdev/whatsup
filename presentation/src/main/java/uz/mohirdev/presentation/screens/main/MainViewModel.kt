package uz.mohirdev.presentation.screens.main

import com.github.terrakok.cicerone.Router
import uz.mohirdev.presentation.base.BaseViewModel
import uz.mohirdev.presentation.navigation.Screens.Phone
import uz.mohirdev.presentation.screens.main.MainViewModel.*

class MainViewModel(
    private val router: Router
) : BaseViewModel<State, Input, Effect>() {

    class State

    class Input

    class Effect

    init {
        router.newRootScreen(Phone())
    }

    override fun getDefaultState() = State()

    override fun processInput(input: Input) {

    }
}