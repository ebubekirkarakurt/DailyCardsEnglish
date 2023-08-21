package com.ekasoftware.english.view.booksandstories.stories.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.ekasoftware.english.assets.Screen
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
                    .padding(end = 15.dp, top = 10.dp)
                    .background(Color.White)
                    .fillMaxSize(),
            ) {
                Column {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){

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

                        Button(
                            modifier = Modifier
                                .padding(
                                    top = 10.dp,
                                    bottom = 10.dp,
                                    start = 10.dp ,
                                    end = 25.dp
                                ),
                            onClick = {
                                speechViewModel.textToSpeech(context, story.ENGtext)
                            },
                            enabled = state.isButtonEnabled

                        ) {
                            Text(text = "speak")
                        }
                    }


                    val painter = rememberImagePainter(data = story.imageResource )

                    Image(painter = painter,
                        modifier = Modifier
                            .padding(15.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .fillMaxWidth(),
                        contentScale = ContentScale.Fit,
                        contentDescription = null
                    )

                }

                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                ) {

                    Text(
                        text = story.title,
                        modifier = Modifier.padding(10.dp),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
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
