package ua.sviatkuzbyt.catfact.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import ua.sviatkuzbyt.catfact.data.structures.Fact
import ua.sviatkuzbyt.catfact.ui.elements.ButtonNext
import ua.sviatkuzbyt.catfact.ui.elements.Card
import ua.sviatkuzbyt.catfact.ui.elements.TopBar

@Composable
fun MainScreen(){
    Column {
        TopBar {  }
        Card(
            Fact(
                text = "Cats are great companions and can be very affectionate.",
                imageUrl = "https://cataas.com/cat"
            )
        )

        ButtonNext(false){}
    }
}