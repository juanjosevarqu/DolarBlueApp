package com.varqulabs.dolarblue.core.user.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.varqulabs.dolarblue.auth.domain.model.AuthResult
import com.varqulabs.dolarblue.auth.domain.model.LoginRequest
import com.varqulabs.dolarblue.core.user.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(private val firebaseService: FirebaseAuth) : AuthRepository {

    override fun login(loginRequest: LoginRequest): Flow<AuthResult<Unit>> {
        return flow {
            try {
                firebaseService.signInWithEmailAndPassword(
                    loginRequest.email,
                    loginRequest.password
                ).await()
                emit(AuthResult.Success(Unit))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(AuthResult.Failure(e))
            }
        }
    }

}