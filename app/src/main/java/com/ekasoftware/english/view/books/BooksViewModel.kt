package com.ekasoftware.english.view.books

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class BookListViewModel : ViewModel() {

    val bookList = mutableStateListOf<Book>()

    init {

        BookList.booklist.forEach { book ->
            bookList.add(book)
        }
    }
}