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
import com.varqulabs.dolarblue.settings.presentation.components.childrens.OptionTogglelable

@Composable
fun ThemeConfigs(
    darkThemeEnabled: Boolean,
    modifier: Modifier = Modifier,
    onClickToggle: (Boolean) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Text(
            text = stringResource(id = R.string.copy_theme).uppercase(),
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 16.sp),
            color = MaterialTheme.colorScheme.primary
        )

        OptionTogglelable(
            title = stringResource(id = R.string.copy_dark_mode),
            checked = darkThemeEnabled,
            modifier = Modifier.fillMaxWidth(),
            onClickToggle = onClickToggle
        )
    }
}