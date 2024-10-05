package com.varqulabs.dolarblue.auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.varqulabs.dolarblue.R
import com.varqulabs.dolarblue.auth.data.useCases.LoginWithEmailAndPasswordUseCase
import com.varqulabs.dolarblue.auth.data.useCases.ResetPasswordUseCase
import com.varqulabs.dolarblue.auth.data.useCases.SendEmailVerifiedUseCase
import com.varqulabs.dolarblue.auth.data.useCases.SignInWithGoogleAccountUseCase
import com.varqulabs.dolarblue.auth.data.useCases.VerifiedAccountUseCase
import com.varqulabs.dolarblue.auth.domain.UserValidator
import com.varqulabs.dolarblue.auth.domain.model.AuthRequest
import com.varqulabs.dolarblue.auth.presentation.login.LoginEvent.OnBack
import com.varqulabs.dolarblue.auth.presentation.login.LoginEvent.OnForgotPasswordVisibility
import com.varqulabs.dolarblue.auth.presentation.login.LoginEvent.OnClickLogin
import com.varqulabs.dolarblue.auth.presentation.login.LoginEvent.OnClickLoginWithGoogle
import com.varqulabs.dolarblue.auth.presentation.login.LoginEvent.OnEmailChange
import com.varqulabs.dolarblue.auth.presentation.login.LoginEvent.OnRecoveryEmailChange
import com.varqulabs.dolarblue.auth.presentation.login.LoginEvent.OnPasswordChange
import com.varqulabs.dolarblue.auth.presentation.login.LoginEvent.OnRecoverAccountClick
import com.varqulabs.dolarblue.auth.presentation.login.LoginEvent.OnRegisterClick
import com.varqulabs.dolarblue.auth.presentation.login.LoginUiEffect.GoBack
import com.varqulabs.dolarblue.auth.presentation.login.LoginUiEffect.GoRegister
import com.varqulabs.dolarblue.auth.presentation.login.LoginUiEffect.ShowError
import com.varqulabs.dolarblue.auth.presentation.login.LoginUiEffect.SuccessLogin
import com.varqulabs.dolarblue.auth.presentation.login.LoginUiEffect.CheckYourEmail
import com.varqulabs.dolarblue.core.domain.DataState
import com.varqulabs.dolarblue.core.presentation.ui.UiText
import com.varqulabs.dolarblue.core.presentation.ui.UiText.DynamicString
import com.varqulabs.dolarblue.core.presentation.ui.UiText.StringResource
import com.varqulabs.dolarblue.core.presentation.utils.mvi.MVIContract
import com.varqulabs.dolarblue.core.presentation.utils.mvi.mviDelegate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val verifiedAccountUseCase: VerifiedAccountUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase,
    private val sendEmailVerifiedUseCase: SendEmailVerifiedUseCase,
    private val loginWithEmailAndPasswordUseCase: LoginWithEmailAndPasswordUseCase,
    private val signInWithGoogleAccountUseCase: SignInWithGoogleAccountUseCase
) : ViewModel(), MVIContract<LoginState, LoginEvent, LoginUiEffect> by mviDelegate(LoginState()) {

    override fun eventHandler(event: LoginEvent) {
        when (event) {
            is OnBack -> emitNavigationBack()
            is OnRegisterClick -> emitNavigationRegister()
            is OnForgotPasswordVisibility -> forgotPasswordVisibility()
            is OnEmailChange -> updateEmail(event.email)
            is OnPasswordChange -> updatePassword(event.password)
            is OnRecoveryEmailChange -> updateRecoveryEmail(event.recoveryEmail)
            is OnRecoverAccountClick -> onRecoverAccount()
            is OnClickLogin -> onLogin()
            is OnClickLoginWithGoogle -> signInWithGoogleAccount(event.credential)
        }
    }

    private fun emitNavigationBack() = viewModelScope.emitEffect(GoBack)

    private fun emitNavigationRegister() = viewModelScope.emitEffect(GoRegister)

    private fun emitError(error: String) = viewModelScope.emitEffect(ShowError(DynamicString(error)))

    private fun emitCheckYourEmail() = viewModelScope.emitEffect(CheckYourEmail(StringResource(R.string.copy_check_your_email_for_recover)))

    private fun emitSuccessLogin() = viewModelScope.emitEffect(SuccessLogin(StringResource(R.string.copy_success_login)))

    private fun forgotPasswordVisibility() = updateUi { copy(showDialogForgotPassword = !uiState.value.showDialogForgotPassword) }

    private fun updateEmail(email: String) {
        updateUi { copy(email = email, emailError = validErrorEmail(email)) }
    }

    private fun updatePassword(password: String) {
        updateUi { copy(password = password, passwordError = validPassword(password)) }
    }

    private fun updateRecoveryEmail(email: String) {
        updateUi { copy(recoveryEmail = email, recoveryEmailError = validErrorEmail(email)) }
    }

    private fun onRecoverAccount() {
        viewModelScope.launch(Dispatchers.IO) {
            if (uiState.value.recoveryEmailError == null) {
                updateUi { copy(showDialogForgotPassword = !uiState.value.showDialogForgotPassword) }
                resetPasswordUseCase.execute(uiState.value.recoveryEmail).collectLatest { dataState ->
                    updateUiAndHandleSuccess(dataState) {
                        updateUi {
                            copy(
                                isLoading = false,
                                recoveryEmail = "",
                            )
                        }
                        emitCheckYourEmail()
                    }
                }
            }
        }
    }

    private fun onLogin() {
        viewModelScope.launch(Dispatchers.IO) {
            loginWithEmailAndPasswordUseCase.execute(
                AuthRequest(
                    email = uiState.value.email,
                    password = uiState.value.password
                )
            ).collectLatest { dataState ->
                updateUiAndHandleSuccess(dataState) { sendVerificationEmail() }
            }
        }
    }

    private fun sendVerificationEmail() {
        viewModelScope.launch {
            sendEmailVerifiedUseCase.execute(Unit).collectLatest { dataState ->
                updateUiAndHandleSuccess(dataState) {
                    updateUi { copy(showDialogConfirmYourEmail = true) }
                    listenerEmailVerification()
                }
            }
        }
    }

    private fun listenerEmailVerification() {
        viewModelScope.launch {
            verifiedAccountUseCase.execute(Unit).collectLatest { dataState ->
                updateUiAndHandleSuccess(dataState) {
                    updateUi {
                        copy(
                            showDialogConfirmYourEmail = !it,
                            isLoading = false,
                            email = "",
                            password = ""
                        )
                    }
                    emitSuccessLogin()
                }
            }
        }
    }

    private fun signInWithGoogleAccount(credential: AuthCredential) = viewModelScope.launch {
        signInWithGoogleAccountUseCase.execute(credential).collectLatest { dataState ->
            updateUiAndHandleSuccess(dataState) {
                updateUi {
                    copy(
                        isLoading = false,
                        isError = false
                    )
                }
                emitError("Logged SuccesFully")
            }
        }
    }

    private fun validErrorEmail(email: String): UiText? {
        return when {
            email.isEmpty() -> StringResource(R.string.error_field_empty)
            !UserValidator.isEmailValid(email) -> StringResource(R.string.error_email_invalid)
            else -> null
        }
    }

    private fun validPassword(password: String): UiText? {
        return when {
            password.isEmpty() -> StringResource(R.string.error_field_empty)
            else -> null
        }
    }

    private fun <T> updateUiAndHandleSuccess(
        dataState: DataState<T>,
        onSuccess: (T) -> Unit
    ) {
        when (dataState) {
            is DataState.Error, DataState.NetworkError -> {
                updateUi {
                    emitError(dataState.getErrorMessage())
                    copy(
                        isError = true,
                        isLoading = false
                    )
                }
            }
            is DataState.Loading -> {
                updateUi {
                    copy(
                        isError = false,
                        isLoading = true
                    )
                }
            }
            is DataState.Success -> onSuccess(dataState.data)
        }
    }
}