package uz.mohirdev.presentation.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import uz.mohirdev.presentation.screens.phone.PhoneFragment

object Screens {
    fun Phone() = FragmentScreen { PhoneFragment() }
}