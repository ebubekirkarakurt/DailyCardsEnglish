package com.ekasoftware.english.graph

import Books
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ekasoftware.english.assets.Screen
import com.ekasoftware.english.view.HomeScreen
import com.ekasoftware.english.view.booksandstories.books.model.Book
import com.ekasoftware.english.view.translator.Translate
import com.ekasoftware.english.view.booksandstories.stories.model.Story
import com.ekasoftware.english.view.chatbot.ui.ChatScreen
import com.ekasoftware.english.view.mynotes.ui.AddNote.AddNote
import com.ekasoftware.english.view.mynotes.ui.UpdateNote.UpdateNote
import com.ekasoftware.english.view.booksandstories.stories.view.StoryDetails
import com.ekasoftware.english.view.tense.model.Tense
import com.ekasoftware.english.view.tense.view.TenseScreen
import com.ekasoftware.english.view.translator.viewmodel.TranslatorViewModel
import com.ekasoftware.english.view.vocablarylist.allWords.view.AllWordScreen
import com.ekasoftware.english.view.vocablarylist.allWords.model.Word
import com.ekasoftware.english.view.vocablarylist.memorization.MemorizationScreen
import com.ekasoftware.english.view.vocablarylist.voclistscreen.VocListScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    translatorViewModel: TranslatorViewModel,
    wordList : List<Word>,
    tenseList : List<Tense>,
    booksList : List<Book>,
    storyList : List<Story>
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(route = Screen.Home.route) {
            HomeScreen(navController = navController)
        }

        /*composable(route = Screen.Settings.route) {
            Settings(navController = navController)
        }*/

        composable(route = Screen.Books.route) {
            Books(
                navController = navController,
                storyList = storyList,
                bookList = booksList
            )
        }

        composable(route = Screen.AddNote.route) {
            AddNote(navController = navController)
        }

        composable(
            route = "updatenote/{id}/{title}/{comment}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                },
                navArgument("title") {
                    type = NavType.StringType
                },
                navArgument("comment") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            val title = backStackEntry.arguments?.getString("title") ?: ""
            val comment = backStackEntry.arguments?.getString("comment") ?: ""

            UpdateNote(navController = navController, id = id, title = title,comment = comment)
        }

        composable(
            route = "${Screen.StoryDetails.route}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ){ backStackEntry ->
            val index = backStackEntry.arguments?.getInt("id") ?: 0
            StoryDetails(navController = navController, storyList = storyList,index = index)
        }

       /* composable(
            route = "${Screen.BookDetails.route}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) { backStackEntry ->
            val index = backStackEntry.arguments?.getInt("id") ?: 0
            BookDetails(navController = navController, booksList = booksList,index = index)
        }*/

        composable(route = Screen.Translate.route) {
            Translate(navController = navController, viewModel = translatorViewModel)
        }

        composable(route = Screen.TenseScreen.route){
            TenseScreen(navController = navController, tenseList = tenseList)
        }

        composable(route = Screen.ChatBot.route) {
            ChatScreen(navController)
        }
        composable(
            route = "${Screen.AllWordScreen.route}",
            arguments = listOf(
                navArgument("index") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        ) { backStackEntry ->
            val index = backStackEntry.arguments?.getInt("index") ?: 0
            AllWordScreen(navController = navController, wordList = wordList, itemIndex = index)
        }
        composable(route = Screen.VocListScreen.route){
            VocListScreen(navController = navController)
        }

        composable(route = Screen.Memorization.route){
            MemorizationScreen(navController = navController)
        }

    }
}
