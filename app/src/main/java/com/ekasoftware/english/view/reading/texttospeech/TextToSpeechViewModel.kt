package com.ekasoftware.english.view.reading.texttospeech

import android.content.Context
import android.speech.tts.TextToSpeech
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.util.*

class TextToSpeechViewModel : ViewModel() {

    private val _state = mutableStateOf(ScreenState())
    val state: State<ScreenState> = _state
    private var textToSpeech: TextToSpeech? = null


    fun textToSpeech(context: Context, text: String) {
        _state.value = state.value.copy(isButtonEnabled = false)
        textToSpeech = TextToSpeech(
            context
        ) {
            if (it == TextToSpeech.SUCCESS) {
                textToSpeech?.let { txtToSpeech ->
                    txtToSpeech.language = Locale.US
                    txtToSpeech.setSpeechRate(1.0f)
                    txtToSpeech.speak(
                        text,
                        TextToSpeech.QUEUE_ADD,
                        null,
                        null
                    )
                }
            }
            _state.value = state.value.copy(isButtonEnabled = true)
        }
    }


    fun stopTextToSpeech() {
        textToSpeech?.stop()
    }

}
