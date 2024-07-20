package com.varqulabs.dolarblue.core.presentation.desingsystem

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.varqulabs.dolarblue.R

val EyeClosedIconPositive: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.ver1_p)

val EyeClosedIconNegative: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.ver1_n)

val EyeOpenedIconPositive: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.ver2_p)

val EyeOpenedIconNegative: ImageVector
    @Composable
    get() = ImageVector.vectorResource(id = R.drawable.ver2_n)
