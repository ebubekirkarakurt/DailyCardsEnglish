package com.ekasoftware.english.view.vocablarylist.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekasoftware.english.view.vocablarylist.model.Word
import com.ekasoftware.english.view.vocablarylist.network.ApiService
import kotlinx.coroutines.launch

class WordsViewModel : ViewModel() {

    var wordListResponse:List<Word> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

    fun getWordList() {
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                val movieList = apiService.getMovies()
                wordListResponse = movieList
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}