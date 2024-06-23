package com.varqulabs.dolarblue.core.user.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.varqulabs.dolarblue.auth.domain.model.LoginRequest
import com.varqulabs.dolarblue.core.user.domain.model.User
import com.varqulabs.dolarblue.core.user.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(private val firebaseService: FirebaseAuth) : AuthRepository {

    override fun login(loginRequest: LoginRequest): Flow<Result<User>> {
        return flow {
            try {
                val user = firebaseService.signInWithEmailAndPassword(
                    loginRequest.email,
                    loginRequest.password
                ).await()
                emit(
                    Result.success(
                        User(
                            token = user.user?.uid ?: "",
                            userName = user.user?.displayName ?: "",
                            email = user.user?.email ?: "",
                        )
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.failure(e))
            }
        }
    }

    override fun resetPassword(email: String): Flow<Result<Boolean>> {
        return flow {
            try {
                val resetPassword = firebaseService.sendPasswordResetEmail(email).await()
                emit(Result.success(true))
            }catch (e: Exception){
                e.printStackTrace()
                emit(Result.failure(e))
            }
        }
    }

}