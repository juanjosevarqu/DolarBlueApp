package com.varqulabs.dolarblue.settings.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.varqulabs.dolarblue.R
import com.varqulabs.dolarblue.core.presentation.generics.buttons.Button4

@Composable
fun MyAccountActions(
    isLoggedIn: Boolean,
    modifier: Modifier = Modifier,
    onClickChangeMyName: () -> Unit,
    onClickChangeMyPassword: () -> Unit,
    onClickExitMyAccount: () -> Unit,
    onClickSignIn: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Text(
            text = stringResource(id = R.string.copy_my_account).uppercase(),
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 16.sp),
            color = MaterialTheme.colorScheme.primary
        )

        MyAccountButtons(
            modifier = Modifier.fillMaxWidth(0.5F),
            isLoggedIn = isLoggedIn,
            onClickChangeMyName = onClickChangeMyName,
            onClickChangeMyPassword = onClickChangeMyPassword,
            onClickExitMyAccount = onClickExitMyAccount,
            onClickSignIn = onClickSignIn
        )
    }
}

@Composable
private fun MyAccountButtons(
    isLoggedIn: Boolean,
    modifier: Modifier = Modifier,
    onClickChangeMyName: () -> Unit,
    onClickChangeMyPassword: () -> Unit,
    onClickExitMyAccount: () -> Unit,
    onClickSignIn: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        if (isLoggedIn) {

            Button4(
                text = stringResource(id = R.string.copy_change_my_name),
                modifier = Modifier.fillMaxWidth()
            ) { onClickChangeMyName() }

            Button4(
                text = stringResource(id = R.string.copy_change_password),
                modifier = Modifier.fillMaxWidth()
            ) { onClickChangeMyPassword() }

            Button4(
                text = stringResource(id = R.string.copy_exit_my_account),
                modifier = Modifier.fillMaxWidth()
            ) { onClickExitMyAccount() }
        } else {

            Button4(
                text = stringResource(id = R.string.copy_login),
                modifier = Modifier.fillMaxWidth()
            ) { onClickSignIn() }
        }
    }
}