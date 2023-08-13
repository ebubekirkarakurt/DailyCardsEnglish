package com.ekasoftware.english.view.booksandstories.books.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ekasoftware.english.assets.Screen
import com.ekasoftware.english.view.booksandstories.books.assets.BookObjectViewModel
import com.ekasoftware.english.view.booksandstories.stories.assets.StoryObjectViewModel
import com.ekasoftware.english.view.booksandstories.stories.viewmodel.StoryViewModel


@Composable
fun Books(navController: NavHostController) {
    Column {
        BooksList(navController = navController)
    }
}


@Composable
fun BooksList(navController: NavHostController) {

    val viewModel: BookObjectViewModel = viewModel()
    val bookList = viewModel.bookList

    val storyViewModel: StoryObjectViewModel = viewModel()
    val storyList = storyViewModel.storyList

    Column(modifier = Modifier
        .background(Color.White)
        .fillMaxSize())
    {
        Button(
            modifier = Modifier
                .background(Color.Transparent)
                .padding(horizontal = 5.dp, vertical = 10.dp),
            onClick = {
                navController.navigate(Screen.Home.route)
            },
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Black
            )
        )
        {
            Icon(imageVector = Icons.Filled.KeyboardArrowLeft, contentDescription = "backbutton")
        }


        Column()
        {
            Column {
                Text(text = "Macera",
                    modifier = Modifier.padding(10.dp),
                    fontSize = 25.sp)

                Row(
                    Modifier.horizontalScroll(rememberScrollState())
                )
                {
                    for ((id, book) in bookList.withIndex()) {
                        Row(
                            modifier = Modifier
                                .padding(5.dp)

                        ) {

                            Column(modifier = Modifier
                                .clickable {
                                    navController.navigate(route = "booksdetails/"+ id)
                                }
                            ) {
                                Card(
                                    modifier = Modifier
                                        .background(Color.Transparent)
                                        .padding(5.dp),
                                    colors = CardDefaults.cardColors(Color.Transparent)

                                ) {
                                    Image(
                                        painter = painterResource(id = book.img),
                                        modifier = Modifier
                                            .padding(5.dp),
                                        contentDescription = "img"
                                    )

                                }
                                Text(text = book.title,
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally),
                                    fontSize = 20.sp)
                            }

                        }
                    }
                }
            }
           Column {

               Text(text = "Hikayeler",
                   modifier = Modifier.padding(10.dp),
                   fontSize = 25.sp)

               Row(
                   Modifier
                       .horizontalScroll(rememberScrollState())
               ){
                   for ((index, story) in storyList.withIndex()) {
                       Row(
                           modifier = Modifier
                               .padding(5.dp)

                       ) {

                           Column(modifier = Modifier
                               .clickable {
                                   navController.navigate(route = "storydetails/"+ index)
                               }
                           ) {
                               Card(
                                   modifier = Modifier
                                       .background(Color.Transparent)
                                       .padding(5.dp),
                                   colors = CardDefaults.cardColors(Color.Transparent)

                               ) {
                                   Image(
                                       painter = painterResource(id = story.img),
                                       modifier = Modifier
                                           .padding(5.dp),
                                       contentDescription = "img"
                                   )

                               }
                               Text(text = story.title,
                                   modifier = Modifier
                                       .align(Alignment.CenterHorizontally),
                                   fontSize = 20.sp)
                           }

                       }
                   }
               }
           }
        }
    }
}

@Preview
@Composable
fun PreviewBookLists() {

    Books(navController = rememberNavController())
}