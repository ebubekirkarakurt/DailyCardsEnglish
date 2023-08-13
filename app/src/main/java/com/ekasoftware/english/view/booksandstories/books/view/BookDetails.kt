package com.ekasoftware.english.view.booksandstories.books.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ekasoftware.english.assets.Screen
import com.ekasoftware.english.view.booksandstories.books.model.Book


@Composable
fun BookDetails(
    navController: NavHostController,
    booksList: List<Book>,
    index: Int?
) {

    val newIndex = index ?: 0

    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Column {
            Button(
                onClick = { navController.navigate(Screen.Books.route) },
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier.padding(10.dp)
            )
            {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = "back",
                    Modifier.background(Color.Transparent),
                    Color.Black
                )
            }
            booksList.forEachIndexed { index, book ->
                if (index == newIndex) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Row {
                            Text(
                                text = book.title,
                                modifier = Modifier.padding(10.dp),
                                fontSize = 20.sp
                            )
                            Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
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
                            text = book.ENGtext,
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
                            text = book.TRtext,
                            fontSize = 16.sp
                        )
                    }
                }
            }


        }

    }
}

