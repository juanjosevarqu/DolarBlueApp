package com.varqulabs.dolarblue.core.user.data.repository

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.varqulabs.dolarblue.auth.domain.model.AuthRequest
import com.varqulabs.dolarblue.core.data.local.preferences.PreferenceKey.USER_SESSION
import com.varqulabs.dolarblue.core.domain.preferences.repository.PreferencesRepository
import com.varqulabs.dolarblue.core.user.data.mappers.toUserSerializable
import com.varqulabs.dolarblue.core.user.domain.model.User
import com.varqulabs.dolarblue.core.user.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AuthRepositoryImpl(
    private val firebaseService: FirebaseAuth,
    private val preferencesRepository: PreferencesRepository
) : AuthRepository {

    override fun logout() = firebaseService.signOut()

    override fun login(loginRequest: AuthRequest): Flow<Boolean> {
        return flow {
            val user = firebaseService.signInWithEmailAndPassword(
                loginRequest.email,
                loginRequest.password
            ).await().user
            saveUserSession(user)
            emit(true)
        }
    }

    override fun signInWithGoogleAccount(credential: AuthCredential): Flow<Boolean> = flow {
        val user = firebaseService.signInWithCredential(credential).await().user
        saveUserSession(user)
        emit(true)
    }

    override fun signUpWithEmailAndPassword(signupRequest: AuthRequest): Flow<Boolean> = flow {
        val user = firebaseService.createUserWithEmailAndPassword(
            signupRequest.email,
            signupRequest.password
        ).await().user
        saveUserSession(user)
        emit(true)
    }

    override fun sendEmailVerified(): Flow<Unit> = flow {
        firebaseService.currentUser?.sendEmailVerification()?.await()
        emit(Unit)
    }

    override fun resetPassword(email: String): Flow<Boolean> {
        return flow {
            firebaseService.sendPasswordResetEmail(email).await()
            emit(true)
        }
    }

    override val verifiedAccount: Flow<Boolean> = flow {
        do {
            val verified = verifyEmailIsVerified()
            emit(verified)
            delay(10000)
        } while (!verified)
    }

    private suspend fun verifyEmailIsVerified(): Boolean {
        return firebaseService.currentUser?.let { user ->
            user.reload().await()
            user.isEmailVerified
        } ?: false
    }

    private suspend fun saveUserSession(user: FirebaseUser?) {
        user?.run {
            val userSession = Json.encodeToString(
                User(
                    token = user.uid,
                    userName = user.displayName.orEmpty(),
                    email = user.email.orEmpty()
                ).toUserSerializable()
            )

            preferencesRepository.putPreference(
                key = USER_SESSION,
                value = userSession
            )
        }
    }


}