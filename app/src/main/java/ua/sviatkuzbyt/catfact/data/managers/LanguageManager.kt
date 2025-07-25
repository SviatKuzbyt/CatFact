package ua.sviatkuzbyt.catfact.data.managers

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.languageStore by preferencesDataStore(name = "language")

class LanguageManager(private val context: Context){
    val languageList = listOf(
        "eng",
        "ben",
        "ces",
        "esp",
        "esp",
        "fil",
        "fra",
        "ger",
        "ita",
        "kor",
        "por",
        "rus",
        "ukr",
        "urd",
        "zho",
        "ben-in",
        "ces-cz",
        "eng-us",
        "esp-es",
        "esp-mx",
        "fil-tl",
        "fra-fr",
        "ger-de",
        "ita-it",
        "kor-ko",
        "por-br",
        "rus-ru",
        "ukr-ua",
        "urd-ud",
        "zho-tw"
    )

    private val langKey = intPreferencesKey("langKey")

    suspend fun getLang() =
        context.languageStore.data.map {
            it[langKey] ?: 0
        }.first()

    suspend fun languageList(value: Int){
        context.languageStore.edit {
            it[langKey] = value
        }
    }
}
