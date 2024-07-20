package com.varqulabs.dolarblue.core.presentation.generics.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.varqulabs.dolarblue.core.presentation.utils.modifier.clickableSingleWithOutRipple

/**
 * Botón 5
 *
 * @param modifier Modificador para aplicar a este Composable.
 * @param text Nombre del botón.
 * @param enabled Boleano para habilitar o deshabilitar el botón.
 * @param color Color del botón.
 * @param isDialogButton Booleando para indicar que el botón es usado en el diálogo y modificar su apariencia.
 * @param onClick Evento para encadenar una acción.
 *
 * @author David Huerta
 */

@Composable
fun Button5(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    color: Color = MaterialTheme.colorScheme.primary,
    isDialogButton: Boolean = false,
    onClick: () -> Unit
) {
    val borderColor = if (enabled) Color.Transparent else MaterialTheme.colorScheme.outline
    val borderWidth = if (enabled) 0.dp else 1.dp
    val backgroundColor = if (enabled) color else Color.White
    val dialogButtonTextColor =
        if (enabled) MaterialTheme.colorScheme.onSurface
        else MaterialTheme.colorScheme.outline
    val textColor =
        if (enabled) MaterialTheme.colorScheme.surfaceContainerLowest
        else MaterialTheme.colorScheme.outline

    Box(
        modifier = modifier
            .width(133.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = borderWidth,
                color = borderColor,
                shape = RoundedCornerShape(8.dp)
            )
            .clickableSingleWithOutRipple {
                if (enabled) onClick()
            }
            .padding(
                vertical = 8.dp,
                horizontal = 5.dp
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = if (isDialogButton) dialogButtonTextColor else textColor
        )
    }
}