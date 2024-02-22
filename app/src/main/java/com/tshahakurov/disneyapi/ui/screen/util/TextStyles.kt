package com.tshahakurov.disneyapi.ui.screen.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

@Composable
fun getAppTextStyle(
    fontSize: Int,
) : TextStyle {
    return TextStyle(
        color = Color.White,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Monospace,
        fontSize = with(LocalDensity.current) {
            LocalContext.current.resources.getDimensionPixelSize(fontSize)
                .toSp()
        })
}