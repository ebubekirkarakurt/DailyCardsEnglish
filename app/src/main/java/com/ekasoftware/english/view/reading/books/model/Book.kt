package com.ekasoftware.english.view.reading.books.model

data class Book(
    var id : Int,
    var title : String,
    var ENGtext : String,
    var TRtext : String,
    var imageResource : String,
    var categoryId : Int
)
