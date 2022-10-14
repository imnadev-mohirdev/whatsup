package uz.mohirdev.data.remote.auth

import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import uz.mohirdev.domain.model.ActivityHolder
import uz.mohirdev.domain.model.InvalidCredentialsException
import java.util.concurrent.TimeUnit

class AuthFirebaseImpl(
    private val activityHolder: ActivityHolder
) : AuthFirebase {

    private val auth = FirebaseAuth.getInstance()

    lateinit var verificationId: String
    lateinit var token: ForceResendingToken

    override fun sendSmsCode(phone: String): Completable = Completable.create {

        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {

            }

            override fun onVerificationFailed(e: FirebaseException) {
                it.onError(e)
            }

            override fun onCodeSent(verificationId: String, token: ForceResendingToken) {
                this@AuthFirebaseImpl.verificationId = verificationId
                this@AuthFirebaseImpl.token = token
                it.onComplete()
            }
        }

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phone)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activityHolder.activity)
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    override fun verify(code: String): Single<FirebaseUser> = Single.create {
        val credential = PhoneAuthProvider.getCredential(verificationId, code)

        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful && isLoggedIn) {
                    it.onSuccess(auth.currentUser!!)
                } else {
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        it.onError(InvalidCredentialsException())
                    }
                }
            }
    }

    override val isLoggedIn: Boolean
        get() = auth.currentUser != null

    override val userId: String?
        get() = auth.currentUser?.uid
}