package ua.sviatkuzbyt.catfact.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.ui.Modifier
import ua.sviatkuzbyt.catfact.ui.CatFactTheme
import ua.sviatkuzbyt.catfact.ui.Theme
import ua.sviatkuzbyt.catfact.ui.screen.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CatFactTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Theme.colors.background)
                        .windowInsetsPadding(WindowInsets.systemBars),
                    content = { MainScreen() }
                )
            }
        }
    }
}