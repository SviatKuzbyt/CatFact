package ua.sviatkuzbyt.catfact.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.androidx.compose.koinViewModel
import ua.sviatkuzbyt.catfact.data.structures.Fact
import ua.sviatkuzbyt.catfact.ui.elements.ButtonNext
import ua.sviatkuzbyt.catfact.ui.elements.Card
import ua.sviatkuzbyt.catfact.ui.elements.TopBar

@Composable
fun MainScreen(){
    val viewModel: MainViewModel = koinViewModel()
    val cardState by viewModel.card.collectAsState()
    Column {
        TopBar {  }

        when(cardState){
            is CardState.Error -> ""
            CardState.Loading -> ""
            is CardState.Success -> Card((cardState as CardState.Success).fact)
        }
        ButtonNext{
            viewModel.loadFact()
        }
    }
}