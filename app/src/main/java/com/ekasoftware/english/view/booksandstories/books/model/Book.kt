package com.ekasoftware.english.view.booksandstories.books.model

data class Book(
    var id : Int,
    var title : String,
    var ENGtext : String,
    var TRtext : String,
    var categoryId : Int
)
