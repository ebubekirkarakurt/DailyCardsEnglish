package com.ekasoftware.english.view

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.ekasoftware.english.R
import com.ekasoftware.english.assets.Screen
import com.ekasoftware.english.statusBarColor
import com.ekasoftware.english.view.mynotes.model.NoteDatabase
import com.ekasoftware.english.view.mynotes.repository.NoteRepository
import com.ekasoftware.english.view.mynotes.ui.NoteList.NoteList
import com.ekasoftware.english.view.mynotes.viewmodel.NoteViewModelProviderFactory
import com.ekasoftware.english.view.mynotes.viewmodel.NotesViewModel


@Composable
fun HomeScreen(navController: NavHostController) {

    statusBarColor(statusBarColor = Color.White)

    Surface(modifier = Modifier
        .fillMaxSize()
    ) {
       Column(modifier = Modifier.background(Color.White)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),

                horizontalArrangement = Arrangement.SpaceAround
            )
            {

                Image(painter = painterResource(id = R.drawable.olaenglisgtopbar),
                    contentDescription = "")
            }
            Column {
                MainCard(navController)
                SpecialCardsMenu(navController)
            }
        }

    }
}

@Composable
fun MainCard(navController: NavHostController) {

    Box(modifier = Modifier.padding(15.dp)) {
        Box(modifier = Modifier) {
            Image(
                painter = painterResource(id = R.drawable.olabotimage),
                contentDescription = "maincard"
            )
            Box(modifier = Modifier
                .background(Color.Transparent)
                .clip(CircleShape)
                .align(Alignment.BottomStart)){
                Button(
                    onClick = { navController.navigate(Screen.ChatBot.route) },
                    modifier = Modifier
                        .padding(5.dp)
                        .wrapContentSize()
                        .clip(shape = CircleShape)
                        .background(Color.Transparent)
                        .align(Alignment.BottomStart),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Black
                    )
                ) {

                    Text(text = "OLAbot ile konus",
                        fontStyle = FontStyle.Italic)

                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowRight,
                        contentDescription = null
                    )

                }
            }

        }
    }
}

@Composable
fun SpecialCardsMenu(navController: NavHostController) {

    val menuItems = listOf("Öğren", "Notlarım")
    val selectedTab = remember { mutableStateOf(0) }

    val noteRepository = NoteRepository(NoteDatabase(LocalContext.current.applicationContext))
    val viewModel: NotesViewModel = viewModel(
        factory = NoteViewModelProviderFactory(app = LocalContext.current.applicationContext as Application, noteRepository = noteRepository)
    )

    TabRow(
        modifier = Modifier.padding(start = 20.dp, end = 20.dp),
        selectedTabIndex = selectedTab.value,
        contentColor = Color.Black,
        containerColor = Color.Transparent
    )
    {
        menuItems.forEachIndexed { index, text ->
            Tab(selected = selectedTab.value == index,
                onClick = {
                    selectedTab.value = index
                },
                Modifier.padding(5.dp)){

                Text(text = text, Modifier.padding(bottom = 5.dp))
            }
        }
    }

    when (selectedTab.value) {
        0 -> SpecialCards(navController = navController)
        1 -> NoteList(navController = navController,viewModel = viewModel)
    }
}


@SuppressLint("SuspiciousIndentation")
@Composable
fun SpecialCards(navController: NavHostController){


    val cards: List<Int> = listOf(
        R.drawable.kelimelisteleri,
        R.drawable.translator,
        R.drawable.kitaplarvehikayeler,
        R.drawable.newezber,

    )

    val cardsNav: List<String> = listOf(
        Screen.VocListScreen.route,
        Screen.Translate.route,
        Screen.Books.route,
        Screen.Memorization.route,
    )


    Column {

        LazyVerticalGrid(
            columns = GridCells.Adaptive(135.dp),
            contentPadding = PaddingValues(
                start = 5.dp,
                top = 10.dp,
                end = 5.dp,
                bottom = 10.dp
            ),
            verticalArrangement = Arrangement.spacedBy(1.dp),
            horizontalArrangement = Arrangement.spacedBy(1.dp),
            content = {
                items(cards.size) { index ->

                    Row (verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center){

                        Box(
                            Modifier
                                .padding(end = 15.dp, bottom = 5.dp)
                                .align(Alignment.CenterVertically)
                                .clickable {
                                    navController.navigate(cardsNav[index])
                                }){
                            Image(
                                painter = painterResource(id = cards[index]),
                                contentDescription = "icons",
                                Modifier
                                    .fillMaxSize()
                                    .padding(start = 15.dp, top = 5.dp)
                            )
                        }
                    }

                }
            }
        )
        Column(modifier = Modifier.fillMaxWidth()
        ){
            Image(painter = painterResource(id =  R.drawable.kelimeezber),
                modifier = Modifier
                    .clickable {
                        navController.navigate(Screen.TenseScreen.route)
                    }
                    .align(Alignment.CenterHorizontally)
                    .size(300.dp)
                    .padding(10.dp, bottom = 20.dp)
                    .fillMaxWidth(),
                contentDescription = "")
        }

    }
}


