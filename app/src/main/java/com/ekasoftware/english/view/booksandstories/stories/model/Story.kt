package com.ekasoftware.english.view.booksandstories.stories.model

data class Story(
    var id : Int,
    var title : String,
    var ENGtext : String,
    var TRtext : String,
    var imageResource : String,
    var categoryId : Int
)