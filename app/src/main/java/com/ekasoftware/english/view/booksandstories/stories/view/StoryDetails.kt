package com.ekasoftware.english.view.booksandstories.stories.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ekasoftware.english.assets.Screen
import com.ekasoftware.english.view.booksandstories.stories.assets.StoryObjectViewModel
import com.ekasoftware.english.view.booksandstories.stories.model.Story
import com.ekasoftware.english.view.booksandstories.texttospeech.TextToSpeechViewModel

@Composable
fun StoryDetails(
    navController: NavHostController,
    storyList: List<Story>,
    index: Int?
) {

    val newIndex = index ?: 0

    val speechViewModel: TextToSpeechViewModel = viewModel()
    val state = speechViewModel.state.value
    val context = LocalContext.current

    storyList.forEachIndexed { index, story ->
        if (newIndex == index) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize(),
            ) {
                Button(

                    onClick = {
                        navController.navigate(Screen.Books.route)
                        speechViewModel.stopTextToSpeech()
                    },

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier.padding(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "back"
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = story.title,
                        modifier = Modifier.padding(10.dp),
                        fontSize = 20.sp
                    )
                    Button(
                        onClick = {
                            speechViewModel.textToSpeech(context, story.ENGtext)
                        },
                        enabled = state.isButtonEnabled

                    ) {
                        Text(text = "speak")
                    }

                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp)
                ) {
                    Text(
                        text = story.ENGtext,
                        fontSize = 16.sp
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp)
                ) {
                    Text(
                        text = story.TRtext,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}
