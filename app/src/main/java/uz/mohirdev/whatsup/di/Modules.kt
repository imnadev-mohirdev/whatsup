package uz.mohirdev.whatsup.di

import com.github.terrakok.cicerone.Cicerone
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import uz.mohirdev.data.local.user.UserStorage
import uz.mohirdev.data.local.user.UserStorageImpl
import uz.mohirdev.data.remote.auth.AuthFirebase
import uz.mohirdev.data.remote.auth.AuthFirebaseImpl
import uz.mohirdev.data.repo.AuthRepositoryImpl
import uz.mohirdev.domain.repo.AuthRepository
import uz.mohirdev.domain.usecase.auth.SendSmsCodeUseCase
import uz.mohirdev.presentation.screens.main.MainViewModel
import uz.mohirdev.presentation.screens.phone.PhoneViewModel

private val cicerone = Cicerone.create()

val appModule = module {
    single { cicerone.router }
    single { cicerone.getNavigatorHolder() }
}

val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
}

val useCaseModule = module {
    single { SendSmsCodeUseCase(get()) }
}

val localModule = module {
    single<UserStorage> { UserStorageImpl() }
}

val remoteModule = module {
    single<AuthFirebase> { AuthFirebaseImpl() }
}

val viewModelModule = module {
    viewModel { PhoneViewModel(get()) }
    viewModel { MainViewModel(get()) }
}