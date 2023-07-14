package com.ekasoftware.english.assets


sealed class Screen(val route : String) {

    object Home: Screen(route = "home_screen")
    object Settings : Screen(route = "settings")
    object Books : Screen(route = "books")
    object Translate : Screen(route = "Translate")
    object ChatBot: Screen(route = "chatbot")
    object BookDetails : Screen(route = "booksdetails/{id}")
    object StoryDetails : Screen(route = "storydetails/{id}")
    object AddNote : Screen(route = "addnote")
    object AllWordScreen : Screen(route = "AllWordScreen")
    object UpdateNote : Screen(route = "updatenote/{id}/{title}/{comment}")
}