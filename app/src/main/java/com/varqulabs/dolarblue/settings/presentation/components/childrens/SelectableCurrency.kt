package com.varqulabs.dolarblue.settings.presentation.components.childrens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.varqulabs.dolarblue.core.presentation.utils.modifier.clickableSingleWithOutRipple

@Composable
fun SelectableCurrency(
    selected: Boolean,
    currencyCode: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .background(
                color = if (selected) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.surfaceVariant,
                shape = MaterialTheme.shapes.small
            )
            .clickableSingleWithOutRipple { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = currencyCode,
            style = if (selected) MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.ExtraBold)
                else MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Medium),
            color = if (selected) MaterialTheme.colorScheme.inverseSurface
                else MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.5F),
            modifier = Modifier.padding(14.dp),
        )
    }
}