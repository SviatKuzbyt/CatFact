package ua.sviatkuzbyt.catfact.data.managers

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import ua.sviatkuzbyt.catfact.data.structures.Language

private val Context.languageStore by preferencesDataStore(name = "language")

class LanguageManager(private val context: Context){
    val languageList = listOf(
        Language("eng-us", "English"),
        Language("ces-cz", "Czech"),
        Language("ger-de", "German"),
        Language("ben-in", "Bengali"),
        Language("esp-es", "Spanish"),
        Language("esp-mx", "Spanish"),
        Language("rus-ru", "Russian"),
        Language("por-br", "Portuguese"),
        Language("tl-fil", "Filipino"),
        Language("ukr-ua", "Ukrainian"),
        Language("urd-ur", "Urdu"),
        Language("ita-it", "Italian"),
        Language("zho-tw", "Chinese"),
        Language("kor-ko", "Korean")
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
