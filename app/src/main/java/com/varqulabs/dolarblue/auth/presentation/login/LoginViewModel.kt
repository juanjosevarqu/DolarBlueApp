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
import com.varqulabs.dolarblue.auth.presentation.login.LoginUiEffect.GoBack
import com.varqulabs.dolarblue.auth.presentation.login.LoginUiEffect.GoRegister
import com.varqulabs.dolarblue.auth.presentation.login.LoginUiEffect.ShowError
import com.varqulabs.dolarblue.auth.presentation.login.LoginUiEffect.SuccessLogin
import com.varqulabs.dolarblue.auth.presentation.login.LoginUiEffect.SuccessSendRecoverAccount
import com.varqulabs.dolarblue.core.domain.DataState
import com.varqulabs.dolarblue.core.presentation.ui.UiText
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
            is LoginEvent.OnBack -> emitNavigationBack()
            is LoginEvent.OnEmailChange -> updateUi {
                copy(
                    email = event.email,
                    emailError = validErrorEmail(event.email)
                )
            }
            is LoginEvent.OnPasswordChange -> {
                updateUi {
                    copy(
                        password = event.password,
                        passwordError = validPassword(event.password)
                    )
                }
            }
            LoginEvent.OnClickLogin -> onLogin()
            is LoginEvent.OnClickLoginWithGoogle -> signInWithGoogleAccount(event.credential)
            LoginEvent.OnRegisterClick -> emitNavigationRegister()
            LoginEvent.OnToggleDialogForgotPasswordClick -> updateUi {
                copy(
                    emailRecover = "",
                    showDialogForgotPassword = !uiState.value.showDialogForgotPassword
                )
            }
            is LoginEvent.OnEmailRecoverChange -> updateUi {
                copy(
                    emailRecover = event.email,
                    emailRecoverError = validErrorEmail(event.email)
                )
            }
            LoginEvent.OnRecoverAccountClick -> onRecoverAccount()
        }
    }

    private fun emitNavigationRegister() = viewModelScope.emitEffect(GoRegister)

    private fun emitSuccessSendRecoverAccount() = viewModelScope.emitEffect(SuccessSendRecoverAccount(UiText.StringResource(R.string.success_send_recover_password)))

    private fun emitNavigationBack() = viewModelScope.emitEffect(GoBack)

    /**
     * Funcion que valida el correo electronico
     *
     * @param email Correo electronico que se va a validar
     * @return Mensaje de error si no es valida la contraseña, si es valida devuelve nulo
     */
    private fun validErrorEmail(email: String): UiText? {
        return when {
            email.isEmpty() -> UiText.StringResource(R.string.error_field_empty)
            !UserValidator.isEmailValid(email) -> UiText.StringResource(R.string.error_email_invalid)
            else -> null
        }
    }

    /**
     * Funcion que valida la contraseña
     *
     * @param password Contraseña que se va a validar
     * @return Mensaje de error si no es valida la contraseña, si es valida devuelve nulo
     */
    private fun validPassword(password: String): UiText? {
        return when {
            password.isEmpty() -> UiText.StringResource(R.string.error_field_empty)
            else -> null
        }
    }

    //Funcion que verifica si puede inicar sesión
    private fun validateAndSetErrors(currentState: LoginState): Boolean {
        val emailError = validErrorEmail(currentState.email)
        val passwordError = validPassword(currentState.password)

        updateUi {
            copy(
                emailError = emailError,
                passwordError = passwordError
            )
        }
        return emailError != null || passwordError != null
    }

    //Funcion que realiza el inicio de sesión
    private fun onLogin() {
        viewModelScope.launch(Dispatchers.IO) {

            val canLogging = validateAndSetErrors(uiState.value)

            if (!canLogging) {
                loginWithEmailAndPasswordUseCase.execute(
                    AuthRequest(
                        email = uiState.value.email,
                        password = uiState.value.password
                    )
                ).collectLatest { dataState ->
                    updateUiStateForDataState(dataState) {
                        sendEmailVerified()
                    }
                }
            }

        }
    }

    //Funcion que envia un email para reperar la cuenta y cambiar la contraseña.
    private fun onRecoverAccount() {
        viewModelScope.launch(Dispatchers.IO) {
            if (uiState.value.emailRecoverError == null) {

                updateUi {
                    copy(
                        showDialogForgotPassword = !uiState.value.showDialogForgotPassword
                    )
                }

                resetPasswordUseCase.execute(uiState.value.emailRecover).collectLatest { dataState ->
                    updateUiStateForDataState(dataState) {
                        updateUi {
                            copy(
                                isLoading = false,
                                emailRecover = "",
                            )
                        }
                        emitSuccessSendRecoverAccount()
                    }
                }
            }
        }
    }

    //Funcion que envia el email para verificar el correo electronico
    private fun sendEmailVerified() {
        viewModelScope.launch {
            sendEmailVerifiedUseCase.execute(Unit).collectLatest { dataState ->
                updateUiStateForDataState(dataState) {
                    updateUi {
                        copy(
                            isVisibleDialogConfirmEmail = true
                        )
                    }
                    verifiedEmail()
                }
            }
        }
    }

    //Funcion que espera la confimacion de la cuenta de Email.
    private fun verifiedEmail() {
        viewModelScope.launch {
            verifiedAccountUseCase.execute(Unit).collectLatest { dataState ->
                updateUiStateForDataState(dataState) {
                    updateUi {
                        copy(
                            isVisibleDialogConfirmEmail = !it,
                            isLoading = false,
                            email = "",
                            password = ""
                        )
                    }
                    emitEffect(SuccessLogin(UiText.StringResource(R.string.success_login)))
                }
            }
        }
    }

    private fun signInWithGoogleAccount(credential: AuthCredential) = viewModelScope.launch {
        signInWithGoogleAccountUseCase.execute(credential).collectLatest { dataState ->
            updateUiStateForDataState(dataState) {
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

    //Funcion que emite el error
    private fun emitError(error: String) = viewModelScope.emitEffect(ShowError(UiText.DynamicString(error)))

    /**
     * Funcion auxiliar para evitar usar codigo repetitivo
     *
     * @param dataState Estado de la respuesta
     * @param onSuccess Lamba que devuelve cuando la respuesta fue correcta
     */
    private fun <T> updateUiStateForDataState(
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
            DataState.Loading -> {
                updateUi {
                    copy(
                        isError = false,
                        isLoading = true
                    )
                }
            }
            is DataState.Success -> {
                onSuccess(dataState.data)
            }
        }
    }
}