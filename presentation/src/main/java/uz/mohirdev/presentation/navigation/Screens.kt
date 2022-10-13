package uz.mohirdev.presentation.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import uz.mohirdev.presentation.screens.code.CodeFragment
import uz.mohirdev.presentation.screens.home.HomeFragment
import uz.mohirdev.presentation.screens.onboarding.OnboardingFragment
import uz.mohirdev.presentation.screens.phone.PhoneFragment

object Screens {
    fun PhoneScreen() = FragmentScreen { PhoneFragment() }
    fun OnboardingScreen() = FragmentScreen { OnboardingFragment() }
    fun CodeScreen(phone: String) = FragmentScreen { CodeFragment(phone) }
    fun HomeScreen() = FragmentScreen { HomeFragment() }
}