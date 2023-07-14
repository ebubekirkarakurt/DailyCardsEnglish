package com.ekasoftware.english.view.translator.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions

class TranslatorViewModel : ViewModel() {

    private val _state = mutableStateOf(
        MainScreenState()
    )

    val state: State<MainScreenState> = _state

    fun onTextToBeTranslatedChange(text: String) {
        _state.value = state.value.copy(
            textToBeTranslated = text
        )
    }

    var turkhishlanguage = TranslateLanguage.TURKISH
    var englishlanguage = TranslateLanguage.ENGLISH

    fun onSwitchButtonClick() {

        var templanguage = turkhishlanguage
        turkhishlanguage = englishlanguage
        englishlanguage = templanguage

    }

    fun onTranslateButtonClick(
        text: String,
        context: Context
    ) {

        val options = TranslatorOptions
            .Builder()
            .setSourceLanguage(turkhishlanguage)
            .setTargetLanguage(englishlanguage)
            .build()

        val languageTranslator = Translation
            .getClient(options)

        languageTranslator.translate(text)
            .addOnSuccessListener { translatedText ->
                _state.value = state.value.copy(
                    translatedText = translatedText
                )
            }
            .addOnFailureListener {

                Toast.makeText(
                    context,
                    "Downloading started..",
                    Toast.LENGTH_SHORT
                ).show()
                downloadModelIfNotAvailable(languageTranslator, context)
            }

    }

    private fun downloadModelIfNotAvailable(
        languageTranslator: Translator,
        context: Context
    ) {
        _state.value = state.value.copy(
            isButtonEnabled = false
        )

        val conditions = DownloadConditions
            .Builder()
            .requireWifi()
            .build()


        languageTranslator
            .downloadModelIfNeeded(conditions)
            .addOnSuccessListener {
                Toast.makeText(
                    context,
                    "Downloaded model successfully..",
                    Toast.LENGTH_SHORT
                ).show()

                _state.value = state.value.copy(
                    isButtonEnabled = true
                )
            }
            .addOnFailureListener {
                Toast.makeText(
                    context,
                    "Some error occurred couldn't download language model..",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

}

data class MainScreenState(
    val textToBeTranslated:String = "",
    val translatedText:String = "",
    val isButtonEnabled:Boolean = true
)