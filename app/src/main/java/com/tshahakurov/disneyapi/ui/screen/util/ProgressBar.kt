package com.tshahakurov.disneyapi.ui.screen.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LoadingProcessBar(alpha: Float = 1f, bgColor: Color? = null) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .alpha(alpha)
            .background(bgColor ?: Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(Modifier.size(100.dp))
    }
}