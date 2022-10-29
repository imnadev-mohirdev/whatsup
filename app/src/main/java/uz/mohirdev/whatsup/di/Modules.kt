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
import uz.mohirdev.data.remote.files.ImageStorage
import uz.mohirdev.data.remote.files.ImageStorageImpl
import uz.mohirdev.data.remote.messages.MessagesFirestore
import uz.mohirdev.data.remote.messages.MessagesFirestoreImpl
import uz.mohirdev.data.remote.users.UsersFirestore
import uz.mohirdev.data.remote.users.UsersFirestoreImpl
import uz.mohirdev.data.repo.AuthRepositoryImpl
import uz.mohirdev.data.repo.ChatRepositoryImpl
import uz.mohirdev.data.repo.SettingsRepositoryImpl
import uz.mohirdev.domain.model.ActivityHolder
import uz.mohirdev.domain.repo.AuthRepository
import uz.mohirdev.domain.repo.ChatRepository
import uz.mohirdev.domain.repo.SettingsRepository
import uz.mohirdev.domain.usecase.auth.SendSmsCodeUseCase
import uz.mohirdev.domain.usecase.auth.VerifyCodeUseCase
import uz.mohirdev.domain.usecase.chat.GetChatsUseCase
import uz.mohirdev.domain.usecase.chat.GetMessagesUseCase
import uz.mohirdev.domain.usecase.chat.SendImageUseCase
import uz.mohirdev.domain.usecase.chat.SendMessageUseCase
import uz.mohirdev.domain.usecase.settings.GetInitialScreenUseCase
import uz.mohirdev.domain.usecase.settings.OnboardedUseCase
import uz.mohirdev.presentation.screens.chat.ChatViewModel
import uz.mohirdev.presentation.screens.code.CodeViewModel
import uz.mohirdev.presentation.screens.home.HomeViewModel
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
    single { ActivityHolder() }
}

val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single<SettingsRepository> { SettingsRepositoryImpl(get()) }
    single<ChatRepository> { ChatRepositoryImpl(get(), get(), get(), get()) }
}

val useCaseModule = module {
    single { SendSmsCodeUseCase(get()) }
    single { OnboardedUseCase(get()) }
    single { GetInitialScreenUseCase(get(), get()) }
    single { VerifyCodeUseCase(get()) }
    single { GetChatsUseCase(get()) }
    single { GetMessagesUseCase(get()) }
    single { SendMessageUseCase(get()) }
    single { SendImageUseCase(get()) }
}

val localModule = module {
    single<UserStorage> { UserStorageImpl() }
    single<SettingsStorage> { SettingsStorageImpl(get()) }
}

val remoteModule = module {
    single<AuthFirebase> { AuthFirebaseImpl(get()) }
    single<UsersFirestore> { UsersFirestoreImpl() }
    single<MessagesFirestore> { MessagesFirestoreImpl() }
    single<ImageStorage> { ImageStorageImpl() }
}

val viewModelModule = module {
    viewModel { PhoneViewModel(get(), get()) }
    viewModel { MainViewModel(get(), get()) }
    viewModel { OnboardingViewModel(get(), get()) }
    viewModel { CodeViewModel(get(), get()) }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { ChatViewModel(get(), get(), get()) }
}