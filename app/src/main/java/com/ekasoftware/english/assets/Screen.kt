package com.ekasoftware.english.assets


sealed class Screen(val route : String) {

    object Home: Screen(route = "home_screen")
    object Settings : Screen(route = "settings")
    object Books : Screen(route = "books")
    object Translate : Screen(route = "Translate")
    object ChatBot: Screen(route = "chatbot")
    object BookDetails : Screen(route = "booksdetails/{id}")
    object StoryDetails : Screen(route = "storydetails/{id}")
    object TenseScreen : Screen(route = "tenseScreen")
    object AddNote : Screen(route = "addnote")
    object VocListScreen : Screen(route = "vocListScreen")
    object AllWordScreen : Screen(route = "AllWordScreen/{index}")
    object UpdateNote : Screen(route = "updatenote/{id}/{title}/{comment}")
    object Memorization : Screen(route = "memorization")
}