package com.ekasoftware.english.view.reading.books.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekasoftware.english.view.reading.books.model.Book
import com.ekasoftware.english.view.reading.books.network.BooksApiService
import kotlinx.coroutines.launch

class BooksViewModel : ViewModel() {

    var bookListResponse:List<Book> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

    fun getBookList() {
        viewModelScope.launch {
            val apiService = BooksApiService.getInstance()
            try {
                val wordList = apiService.getBooks()
                bookListResponse = wordList
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}