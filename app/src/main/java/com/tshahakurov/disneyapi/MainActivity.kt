package com.tshahakurov.disneyapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.tshahakurov.disneyapi.ui.screen.naviagation.DisneyScreen
import com.tshahakurov.disneyapi.ui.theme.DisneyAPITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DisneyAPITheme {
                DisneyScreen()
//                ProcessBar()
            }
        }
    }
}
