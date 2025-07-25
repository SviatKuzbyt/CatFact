package ua.sviatkuzbyt.catfact.ui.screen

import ua.sviatkuzbyt.catfact.data.structures.Fact

sealed class CardState {
    data object Loading : CardState()
    data class Success(val fact: Fact) : CardState()
    data class Error(val message: String) : CardState()
}