package uz.mohirdev.data.remote.auth

import io.reactivex.rxjava3.core.Completable

interface AuthFirebase {
    fun sendSmsCode(phone: String) : Completable
    fun verify(code: String) : Completable
}