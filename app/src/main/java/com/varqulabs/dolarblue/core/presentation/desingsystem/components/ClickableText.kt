package com.varqulabs.dolarblue.core.presentation.desingsystem.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow

/**
 * Componente de texto reutilizable que permite detectar y manejar clics en el texto.
 *
 * @param text Texto que contendrá la parte clickeable.
 * @param modifier Modificador que se aplicará al componente.
 * @param style Estilo de texto que se aplicará al componente.
 * @param softWrap Indica si el texto debe ajustar automáticamente las líneas. Por defecto es false.
 * @param overflow Estrategia de desbordamiento de texto. Por defecto es TextOverflow.Clip.
 * @param maxLines Número máximo de líneas que se mostrarán. Por defecto es Int.MAX_VALUE.
 * @param onTextLayout Callback que se invocará cuando el diseño del texto cambie.
 * @param onClick Callback que se invocará cuando se detecte un clic en el texto, proporcionando el offset del clic.
 *
 * @author: Elias Mena
 */
@Composable
fun ClickableText(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle.Default,
    softWrap: Boolean = false,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    onClick: (offset: Int) -> Unit
) {

    val layoutResult = remember { mutableStateOf<TextLayoutResult?>(null) }

    val gesture = Modifier.pointerInput(onClick) {
        detectTapGestures(
            onTap = { pos ->
                layoutResult.value?.let { layout ->
                    onClick(layout.getOffsetForPosition(pos))
                }
            }
        )
    }

    BasicText(
        text = text,
        modifier = modifier.then(gesture),
        style = style,
        softWrap = softWrap,
        overflow = overflow,
        maxLines = maxLines,
        onTextLayout = {
            layoutResult.value = it
            onTextLayout(it)
        }
    )
}