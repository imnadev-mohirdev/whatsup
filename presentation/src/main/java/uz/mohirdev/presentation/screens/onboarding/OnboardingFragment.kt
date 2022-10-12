package uz.mohirdev.presentation.screens.onboarding

import android.os.Bundle
import android.view.View
import uz.mohirdev.presentation.base.BaseFragment
import uz.mohirdev.presentation.databinding.FragmentOnboardingBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import uz.mohirdev.presentation.screens.onboarding.OnboardingViewModel.Input

class OnboardingFragment : BaseFragment<FragmentOnboardingBinding>(FragmentOnboardingBinding::inflate){

    private val viewModel : OnboardingViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() = with(binding) {
        next.setOnClickListener {
            viewModel.processInput(Input.Onboarded)
        }
    }
}