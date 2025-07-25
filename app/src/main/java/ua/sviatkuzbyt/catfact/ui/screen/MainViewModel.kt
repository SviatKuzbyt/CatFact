package ua.sviatkuzbyt.catfact.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ua.sviatkuzbyt.catfact.data.managers.FactManager
import ua.sviatkuzbyt.catfact.data.managers.ImageManager
import ua.sviatkuzbyt.catfact.data.structures.Fact

class MainViewModel(
    private val factManager: FactManager,
    private val imageManager: ImageManager,
): ViewModel() {
    private val _card = MutableStateFlow<CardState>(CardState.Loading)
    val card: StateFlow<CardState> = _card

    init { loadFact() }

    fun nextFact() {
        if (_card.value !is CardState.Loading) {
            loadFact()
        }
    }

    private fun loadFact() = viewModelScope.launch(Dispatchers.IO) {
        _card.value = CardState.Loading
        try {
            val fact = factManager.getFactText("ukr")
            val imageUrl = imageManager.getImageUrl()
            _card.value = CardState.Success(Fact(fact.data[0], imageUrl.url))
        } catch (e: Exception) {
            _card.value = CardState.Error(e.message ?: "Unknown error")
        }
    }
}