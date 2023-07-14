package com.ekasoftware.english.view.books

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import com.ekasoftware.english.assets.Screen

@Composable
fun BookDetails(navController: NavHostController, index : Int?)
{
    val viewModel: BookListViewModel = viewModel()
    val bookList = viewModel.bookList

    val newIndex = index ?: 0

    Box(
        modifier = Modifier
            .background(Color.White)
            .verticalScroll(rememberScrollState())


    ) {
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
                    Icon(imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "back",
                        Modifier.background(Color.Transparent),
                        Color.Black)
                }
                Box(modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .aspectRatio(1f),){
                    Image(
                        painter = painterResource(id = bookList[newIndex].img),
                        contentDescription = "bookimg",
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .padding(20.dp),
                    )
                }
            }   


            Text(text = bookList[newIndex].title,
                modifier = Modifier.padding(10.dp),
                fontSize = 24.sp)

            Text(text = bookList[newIndex].explanation,
                modifier = Modifier.padding(10.dp))
        }
    }
}


