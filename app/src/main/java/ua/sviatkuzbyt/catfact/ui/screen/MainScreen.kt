package ua.sviatkuzbyt.catfact.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.androidx.compose.koinViewModel
import ua.sviatkuzbyt.catfact.ui.elements.buttons.ButtonNext
import ua.sviatkuzbyt.catfact.ui.elements.card.InfoCard
import ua.sviatkuzbyt.catfact.ui.elements.TopBar
import ua.sviatkuzbyt.catfact.ui.elements.card.ErrorCard
import ua.sviatkuzbyt.catfact.ui.elements.card.LoadingCard

@Composable
fun MainScreen(){
    val viewModel: MainViewModel = koinViewModel()
    val cardState by viewModel.card.collectAsState()
    Column {
        TopBar {  }

        when(cardState){
            is CardState.Error -> ErrorCard((cardState as CardState.Error).message)
            CardState.Loading -> LoadingCard()
            is CardState.Success -> InfoCard((cardState as CardState.Success).fact)
        }

        ButtonNext{
            viewModel.loadFact()
        }
    }
}