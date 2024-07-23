package com.varqulabs.dolarblue.core.presentation.desingsystem.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.varqulabs.dolarblue.core.presentation.desingsystem.DolarBlueTheme
import com.varqulabs.dolarblue.core.presentation.desingsystem.EyeClosedIconNegative
import com.varqulabs.dolarblue.core.presentation.desingsystem.EyeClosedIconPositive
import com.varqulabs.dolarblue.core.presentation.desingsystem.EyeOpenedIconPositive

/**
 * Campo de texto reutilizable.
 *
 * @param modifier Modificador para aplicar a este Composable.
 * @param state Texto actual del campo de texto.
 * @param startIcon Icono opcional para mostrar al inicio del campo de texto.
 * @param endIcon Icono opcional para mostrar al final del campo de texto.
 * @param onTextChange Callback que se llama cuando cambia el texto.
 * @param hint Texto de sugerencia cuando el campo está vacío.
 * @param title Título opcional para mostrar encima del campo de texto.
 * @param isClickableText Callback opcional que se llama cuando se hace clic en el texto adicional.
 * @param enabled Booleano para indicar si el campo de texto está habilitado.
 * @param keyboardOptions Opciones del teclado.
 * @param keyboardActions Acciones del teclado.
 * @param error Mensaje de error opcional para mostrar debajo del campo de texto.
 * @param isPassword Booleano para indicar si el campo de texto es una contraseña.
 * @param additionalInfo Información adicional opcional para mostrar debajo del campo de texto.
 * @param maxLines Número máximo de líneas del campo de texto.
 *
 * @author Elias Mena
 */
@Composable
fun DolarBlueTextField(
    modifier: Modifier = Modifier,
    state: String,
    startIcon: ImageVector? = null,
    endIcon: ImageVector? = null,
    onTextChange: (String) -> Unit,
    hint: String = "",
    title: String? = null,
    isClickableText: () -> Unit = {},
    enabled: Boolean = true,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    error: String? = null,
    isPassword: Boolean = false,
    additionalInfo: String? = null,
    maxLines: Int = 1,
) {
    var hidePassword by remember { mutableStateOf(true) }
    var isFocused by remember { mutableStateOf(false) }
    val onTextChangeState by rememberUpdatedState(onTextChange)

    Column(modifier = modifier) {
        if (title != null) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        BasicTextField(
            value = state,
            textStyle = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.onBackground,
            ),
            enabled = enabled,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
            visualTransformation = if (isPassword && hidePassword) PasswordVisualTransformation() else VisualTransformation.None,
            maxLines = maxLines,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.05f))
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(6.dp)
                )
                .padding(12.dp)
                .onFocusChanged { isFocused = it.isFocused },
            onValueChange = { onTextChangeState(it) },
            decorationBox = { innerBox ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (startIcon != null) {
                        Icon(
                            imageVector = startIcon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        if (state.isEmpty() && !isFocused) {
                            Text(
                                text = hint,
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        innerBox()
                    }
                    if (endIcon != null) {
                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(
                            imageVector = endIcon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                    }
                    if (isPassword) {
                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(
                            imageVector = if (hidePassword) EyeOpenedIconPositive else EyeClosedIconPositive,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .clip(CircleShape)
                                .clickable { hidePassword = !hidePassword }
                                .padding(5.dp)
                        )
                    }
                }
            }
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (error != null) {
                FormError()
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = error,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            if (additionalInfo != null) {
                Text(
                    modifier = Modifier.clickable { isClickableText() },
                    text = additionalInfo,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onBackground
                    )
                )
            }
        }
    }
}

@Composable
private fun FormError() {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
            .size(10.dp)
    )
}

@Preview(
    showBackground = true,
)
@Composable
private fun DolarBlueTextFieldPreview(

) {
    Column {
        DolarBlueTheme {
            var email by remember {
                mutableStateOf("")
            }
            DolarBlueTextField(state = email,
                startIcon = Icons.Filled
                    .Email,
                error = null,
                additionalInfo = null,
                endIcon = null,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {

                    }
                ),
                title = "Email",
                onTextChange = {
                    email = it
                })

        }

    }
}