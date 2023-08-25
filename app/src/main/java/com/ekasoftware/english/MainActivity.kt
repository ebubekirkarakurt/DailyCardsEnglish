package com.ekasoftware.english

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ekasoftware.english.graph.SetupNavGraph
import com.ekasoftware.english.ui.theme.DailyCardsEnglishTheme
import com.ekasoftware.english.view.reading.books.viewmodel.BooksViewModel
import com.ekasoftware.english.view.mynotes.data.Note
import com.ekasoftware.english.view.tense.viewmodel.TenseViewModel
import com.ekasoftware.english.view.translator.viewmodel.TranslatorViewModel
import com.ekasoftware.english.view.vocablarylist.allWords.viewmodel.WordsViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController


class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    private lateinit var note: Note

    private val wordViewModel by viewModels<WordsViewModel>()
    private val tenseViewModel by viewModels<TenseViewModel>()
    private val booksViewModel by viewModels<BooksViewModel>()


    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        note = Note(
            id = 1,
            title = "Başlık",
            comment = "Yorum"
        )

        setContent {

            DailyCardsEnglishTheme {

                statusBarColor(statusBarColor = Color.White)

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.onPrimary
                ) {

                    navController = rememberNavController()

                    val translatorViewModel: TranslatorViewModel by viewModels()
                    val navController = rememberNavController()

                    val wordList = wordViewModel.wordListResponse
                    wordViewModel.getWordList()

                    val tenseList = tenseViewModel.tenseListResponse
                    tenseViewModel.getTenseList()

                    val booksList = booksViewModel.bookListResponse
                    booksViewModel.getBookList()

                    SetupNavGraph(
                        navController = navController,
                        translatorViewModel = translatorViewModel,
                        wordList = wordList,
                        tenseList = tenseList,
                        booksList = booksList
                    )

                }
            }
        }
    }
}

@Composable
fun statusBarColor(statusBarColor : Color){
    val systemUiController = rememberSystemUiController()

    systemUiController.setStatusBarColor(
        color = statusBarColor
    )

    systemUiController.setNavigationBarColor(
        color = Color.White)
}
