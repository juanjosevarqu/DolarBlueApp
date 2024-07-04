package com.varqulabs.dolarblue.core.user.data.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.varqulabs.dolarblue.core.data.local.preferences.PreferenceKey
import com.varqulabs.dolarblue.core.domain.preferences.repository.PreferencesRepository
import com.varqulabs.dolarblue.core.user.data.mappers.toUserSerializable
import com.varqulabs.dolarblue.auth.domain.model.AuthRequest
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

    override fun login(loginRequest: AuthRequest): Flow<Boolean> {
        return flow {
            val user = firebaseService.signInWithEmailAndPassword(
                loginRequest.email,
                loginRequest.password
            ).await()
            saveUserSession(user)
            emit(true)
        }
    }

    override fun resetPassword(email: String): Flow<Boolean> {
        return flow {
            firebaseService.sendPasswordResetEmail(email).await()
            emit(true)
        }
    }

    override fun signUpWithEmailAndPassword(signupRequest: AuthRequest): Flow<Boolean> = flow {
        val user = firebaseService.createUserWithEmailAndPassword(
            signupRequest.email,
            signupRequest.password
        ).await()
        saveUserSession(user)
        emit(true)
    }

    override val verifiedAccount: Flow<Boolean> = flow {
        while (true) {
            val verified = verifyEmailIsVerified()
            emit(verified)
            delay(1000)
        }
    }

    override fun sendEmailVerified(): Flow<Unit> = flow {
        firebaseService.currentUser?.sendEmailVerification()?.await()
        emit(Unit)
    }


    private suspend fun verifyEmailIsVerified(): Boolean {
        return firebaseService.currentUser?.let { user ->
            user.reload().await()
            user.isEmailVerified
        } ?: false
    }

    private suspend fun saveUserSession(user: AuthResult) {
        val userSession = Json.encodeToString(
            User(
                token = user.user?.uid.orEmpty(),
                userName = user.user?.displayName.orEmpty(),
                email = user.user?.email.orEmpty()
            ).toUserSerializable()
        )
        preferencesRepository.putPreference(
            PreferenceKey.USER_SESSION,
            userSession
        )
    }

}