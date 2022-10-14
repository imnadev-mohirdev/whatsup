package uz.mohirdev.data.remote.users

import com.google.firebase.auth.FirebaseUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import uz.mohirdev.data.remote.users.model.UserDocument

interface UsersFirestore {
    fun saveUser(user: FirebaseUser) : Completable
    fun getUsers() : Single<List<UserDocument>>
}