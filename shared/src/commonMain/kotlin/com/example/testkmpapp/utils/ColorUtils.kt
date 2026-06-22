package com.example.testkmpapp.utils

import androidx.compose.ui.graphics.Color

fun String.toColor(): Color {
    val hex = this.removePrefix("#")
    return when (hex.length) {
        6 -> Color(
            red = hex.substring(0, 2).toInt(16),
            green = hex.substring(2, 4).toInt(16),
            blue = hex.substring(4, 6).toInt(16),
            alpha = 255
        )
        8 -> Color(
            red = hex.substring(2, 4).toInt(16),
            green = hex.substring(4, 6).toInt(16),
            blue = hex.substring(6, 8).toInt(16),
            alpha = hex.substring(0, 2).toInt(16)
        )
        else -> Color.Gray
    }
}
