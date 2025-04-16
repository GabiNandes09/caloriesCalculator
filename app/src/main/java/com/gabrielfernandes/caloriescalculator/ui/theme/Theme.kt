package com.gabrielfernandes.caloriescalculator.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

@Composable
fun CaloriesCalculatorTheme(
    content: @Composable () -> Unit
) {
    val colors = lightColorScheme(
        primary = Color(0xFF8E6E53),
        secondary = Color(0xFF6C4E3D),
        background = Color(0xFFFFFBF2),
        surface = Color(0xFFFFFBF2)
    )

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}