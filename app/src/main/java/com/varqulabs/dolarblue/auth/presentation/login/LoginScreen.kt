package com.varqulabs.dolarblue.auth.presentation.login

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.varqulabs.dolarblue.R
import com.varqulabs.dolarblue.auth.presentation.login.LoginEvent.OnBack
import com.varqulabs.dolarblue.auth.presentation.login.components.LoginForm
import com.varqulabs.dolarblue.core.presentation.generics.top_bars.SimpleAppBar

@Composable
fun LoginScreen(
    context: Context,
    state: LoginState,
    eventHandler: (LoginEvent) -> Unit
) {
    Scaffold(
        topBar = {
            SimpleAppBar(title = "") { eventHandler(OnBack) }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(80.dp)
        ) {

            Image(
                modifier = Modifier.size(64.dp),
                painter = painterResource(id = R.drawable.logo_app),
                contentDescription = stringResource(R.string.content_description_image_logo)
            )

            Text(
                text = stringResource(R.string.welcome_app),
                style = MaterialTheme.typography.headlineLarge
            )

            LoginForm(
                context = context,
                state = state,
                eventHandler = eventHandler
            )
        }
    }
}