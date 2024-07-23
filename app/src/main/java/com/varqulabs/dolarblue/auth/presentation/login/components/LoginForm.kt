package com.varqulabs.dolarblue.auth.presentation.login.components

import android.content.Context
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.varqulabs.dolarblue.R
import com.varqulabs.dolarblue.auth.presentation.login.LoginEvent
import com.varqulabs.dolarblue.auth.presentation.login.LoginState
import com.varqulabs.dolarblue.core.presentation.desingsystem.Nunito
import com.varqulabs.dolarblue.core.presentation.desingsystem.components.ClickableText
import com.varqulabs.dolarblue.core.presentation.desingsystem.components.DolarBlueActionButton
import com.varqulabs.dolarblue.core.presentation.desingsystem.components.DolarBluePasswordTextField
import com.varqulabs.dolarblue.core.presentation.desingsystem.components.DolarBlueTextField


@Composable
fun LoginForm(
    modifier: Modifier = Modifier,
    context: Context,
    state: LoginState,
    eventHandler: (LoginEvent) -> Unit
) {

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        val account = task.getResult(ApiException::class.java)
        val credential = GoogleAuthProvider.getCredential(
            account.idToken,
            null
        )
        eventHandler(LoginEvent.OnClickLoginWithGoogle(credential))
    }

    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
    ) {

        DolarBlueTextField(
            state = state.email,
            error = state.emailError?.asString(context),
            startIcon = null,
            endIcon = null,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            ),
            onTextChange = {
                eventHandler(LoginEvent.OnEmailChange(it))
            },
            title = stringResource(R.string.title_email_field)
        )

        Spacer(modifier = Modifier.height(20.dp))

        DolarBluePasswordTextField(
            state = state.password,
            startIcon = null,
            isClickableText = {
                eventHandler(LoginEvent.OnToggleDialogForgotPasswordClick)
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            error = state.passwordError?.asString(context),
            additionalInfo = stringResource(R.string.text_forgot_password),
            onTextChange = {
                eventHandler(LoginEvent.OnPasswordChange(it))
            },
            title = stringResource(R.string.title_password_field)
        )

        Spacer(modifier = Modifier.height(20.dp))

        DolarBlueActionButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            text = stringResource(R.string.text_button_login),
            onClick = {
                eventHandler(LoginEvent.OnClickLogin)
            }
        )

        val annotatedString = buildAnnotatedString {

            withStyle(
                style = SpanStyle(
                    fontFamily = Nunito,
                    fontWeight = FontWeight.Normal,
                    fontSize = 10.sp,
                    letterSpacing = 0.5.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            ) {

                append(stringResource(id = R.string.dont_have_an_account))

                append("\u00A0".repeat(45))

            }

            pushStringAnnotation(
                tag = "clickable_text",
                annotation = stringResource(id = R.string.register)
            )

            withStyle(
                style = SpanStyle(
                    fontFamily = Nunito,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 10.sp,
                    letterSpacing = 0.5.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            ) {
                append(stringResource(id = R.string.register))
            }

            pop()
        }

        Spacer(modifier = Modifier.height(8.dp))

        ClickableText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            text = annotatedString,
            onClick = { offset ->
                annotatedString.getStringAnnotations(
                    tag = "clickable_text",
                    start = offset,
                    end = offset,
                ).firstOrNull()?.let {
                    eventHandler(LoginEvent.OnRegisterClick)
                }
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        DolarBlueActionButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            text = "tu cola",
            onClick = {
                launcher.launch(getSignupWithGoogleAccountIntent(context))
            }
        )
    }

}

private fun getSignupWithGoogleAccountIntent(context: Context): Intent {
    val options = GoogleSignInOptions.Builder(
        GoogleSignInOptions.DEFAULT_SIGN_IN
    ).requestIdToken(context.getString(R.string.web_client_id))
        .requestEmail().build()
    val client = GoogleSignIn.getClient(
        context,
        options
    )
    return client.signInIntent
}