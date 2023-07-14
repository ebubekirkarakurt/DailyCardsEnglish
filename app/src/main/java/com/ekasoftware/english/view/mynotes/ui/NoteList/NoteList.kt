package com.ekasoftware.english.view.mynotes.ui.NoteList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ekasoftware.english.assets.Screen
import com.ekasoftware.english.ui.theme.UiColor
import com.ekasoftware.english.view.mynotes.data.Note
import com.ekasoftware.english.view.mynotes.viewmodel.NotesViewModel



@Composable
fun NoteList(navController: NavHostController, viewModel: NotesViewModel) {

    val notes: List<Note> by viewModel.getAllNote().observeAsState(initial = emptyList())
    NoteListItems(navController, notes)
}

@Composable
fun NoteListItems(navController: NavHostController, notes: List<Note>) {
    Column(modifier = Modifier) {
        Column(modifier = Modifier
            .height(320.dp)
            .verticalScroll(rememberScrollState())
        ) {

            for (note in notes) {
                Card(modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
                    .clickable {
                        if (note.comment.isNullOrEmpty()) {
                            note.comment = "  "
                        }
                        navController.navigate(route = "updatenote/${note.id}/${note.title}/${note.comment}")
                    }
                ) {
                    Column {
                        Text(
                            text = note.title,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(10.dp)
                        )
                        Text(
                            text = note.comment,
                            fontSize = 15.sp,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }
            }
        }
        Column(modifier = Modifier
            .fillMaxWidth()
        ){
            FloatingActionButton(onClick = { navController.navigate(Screen.AddNote.route) },
                    modifier = Modifier.background(Color.Transparent),
                containerColor = Color.White
            ) {
                Icon(imageVector = Icons.Filled.Add,
                    contentDescription = "")
            }
        }
    }
}

