package com.ekasoftware.english.view.tense.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekasoftware.english.view.tense.model.Tense
import com.ekasoftware.english.view.tense.network.ApiService
import kotlinx.coroutines.launch

class TenseViewModel : ViewModel() {

    var tenseListResponse:List<Tense> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

    fun getTenseList() {
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                val tenseList = apiService.getTense()
                tenseListResponse = tenseList
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}