package ua.sviatkuzbyt.catfact.ui.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ua.sviatkuzbyt.catfact.ui.Theme

@Composable
fun MainScreen(){
    Text(
        text = "Hello, Cat Fact!",
        style = Theme.types.bigBold
    )
}