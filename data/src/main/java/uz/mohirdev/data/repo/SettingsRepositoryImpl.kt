package uz.mohirdev.data.repo

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import uz.mohirdev.data.local.settings.SettingsStorage
import uz.mohirdev.domain.repo.SettingsRepository

class SettingsRepositoryImpl(
    private val settingsStorage: SettingsStorage
) : SettingsRepository {

    override fun onboarded() : Completable = settingsStorage
        .onboarded()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    override fun getOnboarded(): Single<Boolean>  = settingsStorage
        .getOnboarded()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}