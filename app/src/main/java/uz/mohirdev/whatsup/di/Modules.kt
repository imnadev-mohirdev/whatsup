package uz.mohirdev.whatsup.di

import com.github.terrakok.cicerone.Cicerone
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import uz.mohirdev.data.local.settings.SettingsRealm
import uz.mohirdev.data.local.settings.SettingsStorage
import uz.mohirdev.data.local.settings.SettingsStorageImpl
import uz.mohirdev.data.local.user.UserStorage
import uz.mohirdev.data.local.user.UserStorageImpl
import uz.mohirdev.data.remote.auth.AuthFirebase
import uz.mohirdev.data.remote.auth.AuthFirebaseImpl
import uz.mohirdev.data.repo.AuthRepositoryImpl
import uz.mohirdev.data.repo.SettingsRepositoryImpl
import uz.mohirdev.domain.repo.AuthRepository
import uz.mohirdev.domain.repo.SettingsRepository
import uz.mohirdev.domain.usecase.auth.SendSmsCodeUseCase
import uz.mohirdev.domain.usecase.settings.GetOnboardedUseCase
import uz.mohirdev.domain.usecase.settings.OnboardedUseCase
import uz.mohirdev.presentation.screens.main.MainViewModel
import uz.mohirdev.presentation.screens.onboarding.OnboardingViewModel
import uz.mohirdev.presentation.screens.phone.PhoneViewModel

private val cicerone = Cicerone.create()

val config = RealmConfiguration.Builder(schema = setOf(SettingsRealm::class))
    .build()

val appModule = module {
    single { cicerone.router }
    single { cicerone.getNavigatorHolder() }
    single { Realm.open(config) }
}

val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<SettingsRepository> { SettingsRepositoryImpl(get()) }
}

val useCaseModule = module {
    single { SendSmsCodeUseCase(get()) }
    single { OnboardedUseCase(get()) }
    single { GetOnboardedUseCase(get()) }
}

val localModule = module {
    single<UserStorage> { UserStorageImpl() }
    single<SettingsStorage> { SettingsStorageImpl(get()) }
}

val remoteModule = module {
    single<AuthFirebase> { AuthFirebaseImpl() }
}

val viewModelModule = module {
    viewModel { PhoneViewModel(get()) }
    viewModel { MainViewModel(get(), get()) }
    viewModel { OnboardingViewModel(get(), get()) }
}