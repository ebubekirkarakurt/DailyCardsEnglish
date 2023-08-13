package com.ekasoftware.english

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ekasoftware.english.graph.SetupNavGraph
import com.ekasoftware.english.ui.theme.DailyCardsEnglishTheme
import com.ekasoftware.english.view.booksandstories.books.viewmodel.BooksViewModel
import com.ekasoftware.english.view.booksandstories.stories.viewmodel.StoryViewModel
import com.ekasoftware.english.view.booksandstories.texttospeech.TextToSpeechViewModel
import com.ekasoftware.english.view.mynotes.data.Note
import com.ekasoftware.english.view.tense.viewmodel.TenseViewModel
import com.ekasoftware.english.view.translator.viewmodel.TranslatorViewModel
import com.ekasoftware.english.view.vocablarylist.allWords.viewmodel.WordsViewModel
import com.ekasoftware.english.view.vocablarylist.vocablarydb.model.VocablaryDatabase
import com.ekasoftware.english.view.vocablarylist.vocablarydb.repository.VocablaryRepository
import com.ekasoftware.english.view.vocablarylist.vocablarydb.viewmodel.VocablaryViewModel
import com.ekasoftware.english.view.vocablarylist.vocablarydb.viewmodel.VocablaryViewModelProviderFactory


class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    private lateinit var note: Note

    val wordViewModel by viewModels<WordsViewModel>()
    val tenseViewModel by viewModels<TenseViewModel>()
    val booksViewModel by viewModels<BooksViewModel>()
    val storyViewModel by viewModels<StoryViewModel>()


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

                    val storyList = storyViewModel.storyListResponse
                    storyViewModel.getStoryList()

                    SetupNavGraph(
                        navController = navController,
                        translatorViewModel = translatorViewModel,
                        wordList = wordList,
                        tenseList = tenseList,
                        booksList = booksList,
                        storyList = storyList
                    )

                }
            }
        }
    }
}

