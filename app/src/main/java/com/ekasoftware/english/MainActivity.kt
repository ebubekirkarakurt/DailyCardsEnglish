package com.ekasoftware.english

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ekasoftware.english.graph.SetupNavGraph
import com.ekasoftware.english.ui.theme.DailyCardsEnglishTheme
import com.ekasoftware.english.view.mynotes.data.Note
import com.ekasoftware.english.view.translator.viewmodel.TranslatorViewModel
import com.ekasoftware.english.view.vocablarylist.model.Word
import com.ekasoftware.english.view.vocablarylist.view.AllWordScreen
import com.ekasoftware.english.view.vocablarylist.viewmodel.WordsViewModel


class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private lateinit var note: Note
    val wordViewModel by viewModels<WordsViewModel>()

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

                    SetupNavGraph(
                        navController = navController,
                        translatorViewModel = translatorViewModel,
                        wordList = wordList
                    )

                }
            }
        }
    }
}
