package com.ekasoftware.english.view.booksandstories.stories.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekasoftware.english.view.booksandstories.books.model.Book
import com.ekasoftware.english.view.booksandstories.books.network.BooksApiService
import com.ekasoftware.english.view.booksandstories.stories.model.Story
import com.ekasoftware.english.view.booksandstories.stories.network.StoryApiService
import kotlinx.coroutines.launch

class StoryViewModel : ViewModel() {

    var storyListResponse: List<Story> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

    fun getStoryList() {
        viewModelScope.launch {
            val apiService = StoryApiService.getInstance()
            try {
                val storyList = apiService.getStories()
                storyListResponse = storyList
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}