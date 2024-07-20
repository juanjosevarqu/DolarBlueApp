package com.varqulabs.dolarblue.settings.presentation.components.childrens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OptionTogglelable(
    title: String,
    checked: Boolean,
    modifier: Modifier = Modifier,
    onClickToggle: (Boolean) -> Unit
) {
    Row(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.small
            )
            .padding(
                horizontal = 16.dp,
                vertical = 6.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = title,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold, fontSize = 16.sp),
            color = MaterialTheme.colorScheme.inverseSurface
        )

        Switch(
            checked = checked,
            onCheckedChange = { onClickToggle(it) },
            colors = SwitchDefaults.colors(
                checkedTrackColor = MaterialTheme.colorScheme.surfaceVariant,
                checkedBorderColor = MaterialTheme.colorScheme.primary,
                checkedThumbColor = MaterialTheme.colorScheme.primary,
            )
        )
    }
}