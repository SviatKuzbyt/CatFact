package ua.sviatkuzbyt.catfact.ui.screen

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import ua.sviatkuzbyt.catfact.ui.elements.LanguageSheet
import ua.sviatkuzbyt.catfact.ui.elements.buttons.ButtonNext
import ua.sviatkuzbyt.catfact.ui.elements.card.InfoCard
import ua.sviatkuzbyt.catfact.ui.elements.TopBar
import ua.sviatkuzbyt.catfact.ui.elements.card.ErrorCard
import ua.sviatkuzbyt.catfact.ui.elements.card.LoadingCard

@Composable
fun MainScreen(){
    val viewModel: MainViewModel = koinViewModel()
    val cardState by viewModel.card.collectAsState()
    val languageState by viewModel.language.collectAsState()

    Column {
        TopBar {
            viewModel.changeLanguageVisibility()
        }

        Crossfade(
            targetState = cardState,
            modifier = Modifier.fillMaxWidth().weight(1f)
        ){
            when(it){
                is CardState.Error -> ErrorCard(it.errorTexts)
                CardState.Loading -> LoadingCard()
                is CardState.Success -> InfoCard(it.fact)
            }
        }

        ButtonNext{
            viewModel.nextFact()
        }

        if (languageState is LanguageState.Visible) {
            val state = languageState as LanguageState.Visible
            LanguageSheet(
                languages = state.list,
                selectedIndex = state.selectedIndex,
                onSelect = {
                    viewModel.setLanguage(it)
                },
                onClose = {
                    viewModel.changeLanguageVisibility()
                }
            )
        }
    }
}