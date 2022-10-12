package uz.mohirdev.data.local.settings

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface SettingsStorage {
    fun onboarded() : Completable
    fun getOnboarded() : Single<Boolean>
}