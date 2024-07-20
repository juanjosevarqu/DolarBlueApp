package com.varqulabs.dolarblue.core.presentation.desingsystem.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.varqulabs.dolarblue.R
import com.varqulabs.dolarblue.core.presentation.desingsystem.LocalTheme
import com.varqulabs.dolarblue.core.presentation.generics.buttons.Button5

/**
 * Diálogo reutilizable.
 *
 * @param modifier Modificador para aplicar a este Composable.
 * @param title Título del diálogo.
 * @param instructionBody Cuerpo del mensaje/instrucción a mostrar.
 * @param leftButtonText Nombre del botón izquierdo.
 * @param rightButtonText Nombre del botón derecho.
 * @param enabledLeftButton habilitar o deshabilitar el botón izquierdo (opcional).
 * @param enabledRightButton Habilitar o deshabilitar el botón derecho (opcional).
 * @param isLoading Booleano para mostrar un progress indicator en el diálogo si se requiere (opcional).
 * @param isVisibleButtons Booleano para indicar si los botones deben mostrarse o no (opcional).
 * @param onDismiss Callback para cerrar el diálogo.
 * @param onAccept Callback para realizar una acción adicional.
 * @param content Composable para mostrar contenido adicional en el diálogo (opcional).
 *
 * @author David Huerta
 */

@Composable
fun DollarBlueDialog(
    modifier: Modifier = Modifier,
    title: String,
    instructionBody: String,
    leftButtonText: String = stringResource(id = R.string.update_dialog_accept_button),
    rightButtonText: String = stringResource(id = R.string.update_dialog_cancel_button),
    enabledLeftButton: Boolean = true,
    enabledRightButton: Boolean = true,
    isLoading: Boolean = false,
    isVisibleButtons: Boolean = true,
    onDismiss: () -> Unit,
    onAccept: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    val borderWidth = if(!LocalTheme.current.isDark) 2.dp else 0.dp
    val borderColor = if(!LocalTheme.current.isDark) MaterialTheme.colorScheme.primary else Color.Transparent

    Dialog(onDismissRequest = { if (!isLoading) onDismiss() }) {
        ElevatedCard(
            modifier = modifier
                .fillMaxWidth()
                .border(
                    width = borderWidth,
                    color = borderColor,
                    shape = CardDefaults.elevatedShape
                ),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .padding(top = 15.dp, bottom = 25.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title.uppercase(),
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.surfaceContainerLowest,
                        textAlign = TextAlign.Center
                    )
                }
                
                Text(
                    text = instructionBody,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Normal),
                    color = MaterialTheme.colorScheme.inverseSurface,
                )

                content()

                if (isVisibleButtons) {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Button5(
                            text = leftButtonText,
                            enabled = enabledLeftButton
                        ) { onAccept() }

                        Button5(
                            text = rightButtonText,
                            enabled = enabledRightButton,
                            color = Color(0xFF226054),
                            isDialogButton = true
                        ) { onDismiss() }
                    }
                }

                if (isLoading) CircularProgressIndicator()
            }
        }
    }
}