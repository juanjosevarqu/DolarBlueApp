package com.varqulabs.dolarblue.core.presentation.desingsystem.components


import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.varqulabs.dolarblue.core.presentation.desingsystem.DolarBlueTheme
import com.varqulabs.dolarblue.core.presentation.desingsystem.EyeClosedIconPositive

/**
 * Campo de texto de contraseña
 *
 * @param modifier Modificador para aplicar a campo de texto.
 * @param state Texto actual del campo de texto.
 * @param startIcon Icono opcional para mostrar al inicio del campo
 * @param onTextChange Callback que se llama cuando cambia el texto.
 * @param hint Texto de sugerencia cuando el campo está vacío.
 * @param isClickableText Callback opcional que se llama cuando se hace clic en el texto adicional.
 * @param title Título opcional para mostrar encima del campo de texto.
 * @param enabled Booleano para indicar si el campo de texto está habilitado.
 * @param keyboardOptions Opciones del teclado.
 * @param keyboardActions Acciones del teclado.
 * @param error Mensaje de error opcional para mostrar debajo del campo.
 * @param additionalInfo Información adicional opcional para mostrar debajo del campo.
 * @param maxLines Número máximo de líneas del campo de texto.
 *
 * @author Elias Mena
 **/
@Composable
fun DolarBluePasswordTextField(
    modifier: Modifier = Modifier,
    state: String,
    startIcon: ImageVector?,
    onTextChange: (String) -> Unit,
    hint: String = "",
    isClickableText: () -> Unit,
    title: String?,
    enabled: Boolean = true,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    error: String? = null,
    additionalInfo: String? = null,
    maxLines: Int = 1,
) {
    DolarBlueTextField(
        modifier = modifier,
        state = state,
        startIcon = startIcon,
        endIcon = null,
        isPassword = true,
        enabled = enabled,
        isClickableText = isClickableText,
        onTextChange = onTextChange,
        title = title,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        error = error,
        additionalInfo = additionalInfo,
        maxLines = maxLines,
        hint = hint,
    )
}

@Preview(
    showBackground = true,
)
@Composable
private fun DolarBluePasswordTextFieldPreview(

) {
    Column {
        DolarBlueTheme {
            var email by remember {
                mutableStateOf("")
            }
            DolarBluePasswordTextField(state = email,
                startIcon = null,
                error = "Contraseña incorrecta",
                additionalInfo = "¿Olvidaste tu contraseña?",
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {

                    }
                ),
                isClickableText = {},
                title = "Contraseña",
                onTextChange = {
                    email = it
                })

        }

    }
}