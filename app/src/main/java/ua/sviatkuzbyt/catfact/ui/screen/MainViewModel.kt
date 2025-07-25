package ua.sviatkuzbyt.catfact.ui.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ua.sviatkuzbyt.catfact.R
import ua.sviatkuzbyt.catfact.data.managers.FactManager
import ua.sviatkuzbyt.catfact.data.managers.ImageManager
import ua.sviatkuzbyt.catfact.data.managers.LanguageManager
import ua.sviatkuzbyt.catfact.data.structures.ErrorTexts
import ua.sviatkuzbyt.catfact.data.structures.Fact
import ua.sviatkuzbyt.catfact.other.NetworkMonitor

class MainViewModel(
    private val factManager: FactManager,
    private val imageManager: ImageManager,
    private val languageManager: LanguageManager,
    private val networkMonitor: NetworkMonitor
): ViewModel() {
    private val _card = MutableStateFlow<CardState>(CardState.Loading)
    private val _language = MutableStateFlow<LanguageState>(LanguageState.Hidden)
    val card: StateFlow<CardState> = _card
    val language: StateFlow<LanguageState> = _language

    private val langCodes = languageManager.languageList.map { it.code }
    private val langNames = languageManager.languageList.map { it.name }
    private var langSelected = 0

    init {
        getSelectedLanguageIndex()
        loadFact()
    }

    private fun loadFact() = viewModelScope.launch(Dispatchers.IO) {
        _card.value = CardState.Loading
        try {
            if (networkMonitor.isConnected()){
                val fact = factManager.getFactText(langCodes[langSelected])
                val imageUrl = imageManager.getImageUrl()
                _card.value = CardState.Success(Fact(fact.data[0], imageUrl.url))
            } else {
                _card.value = CardState.Error(ErrorTexts(messageRes = R.string.no_internet))
            }

        } catch (e: Exception) {
            _card.value = CardState.Error(
                ErrorTexts(
                    messageRes = R.string.error,
                    message = e.message ?: "Unknown error"
                )
            )
        }
    }

    private fun getSelectedLanguageIndex() = viewModelScope.launch(Dispatchers.IO) {
        langSelected = try {
            languageManager.getLang()
        } catch (e: Exception) {
            Log.e("SKLT", "Error in getSelectedLanguageIndex", e)
            0
        }
    }

    fun nextFact() {
        if (_card.value !is CardState.Loading) {
            loadFact()
        }
    }

    fun changeLanguageVisibility() {
        _language.value = when (_language.value) {
            is LanguageState.Hidden -> LanguageState.Visible(
                list = langNames,
                selectedIndex = langSelected
            )
            is LanguageState.Visible -> LanguageState.Hidden
        }
    }

    fun setLanguage(index: Int) {
        if (index != langSelected) {
            langSelected = index
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    languageManager.languageList(langSelected)
                } catch (e: Exception) {
                    Log.e("SKLT", "Error in setLanguage", e)
                }
            }
        }
        _language.value = LanguageState.Hidden
    }
}