package uz.mohirdev.data.repo

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import uz.mohirdev.data.remote.auth.AuthFirebase
import uz.mohirdev.domain.repo.AuthRepository

class AuthRepositoryImpl constructor(
    private val authFirebase: AuthFirebase
) : AuthRepository {

    override fun sendSmsCode(phone: String) : Completable = authFirebase.sendSmsCode(phone)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    override fun verify(code: String): Completable  = authFirebase.verify(code)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}