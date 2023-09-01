package com.ekasoftware.english.view.mynotes.ui.NoteList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ekasoftware.english.assets.Screen
import com.ekasoftware.english.statusBarColor
import com.ekasoftware.english.ui.theme.TenseColor1
import com.ekasoftware.english.ui.theme.TenseColor2
import com.ekasoftware.english.ui.theme.TenseColor3
import com.ekasoftware.english.ui.theme.TenseColor4
import com.ekasoftware.english.ui.theme.TenseColor5
import com.ekasoftware.english.view.mynotes.data.Note
import com.ekasoftware.english.view.mynotes.viewmodel.NotesViewModel



@Composable
fun NoteList(navController: NavHostController, viewModel: NotesViewModel) {
    statusBarColor(statusBarColor = Color.White)

    val notes: List<Note> by viewModel.getAllNote().observeAsState(initial = emptyList())
    NoteListItems(navController, notes)
}

@Composable
fun NoteListItems(navController: NavHostController, notes: List<Note>) {

    val cardColors = listOf(
        TenseColor1,
        TenseColor2,
        TenseColor3,
        TenseColor4,
        TenseColor5
    )

    Column(
        modifier = Modifier
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        notes.forEachIndexed { index, note ->
            val cardColor = cardColors[index % cardColors.size]

            Card(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth()
                    .background(
                        Color.Transparent
                    )
                    .clickable {
                        if (note.comment.isNullOrEmpty()) {
                            note.comment = "  "
                        }
                        navController.navigate(route = "updatenote/${note.id}/${note.title}/${note.comment}")
                    },
                colors = CardDefaults.cardColors(
                    containerColor = cardColor
                )
            ) {
                Column {
                    Text(
                        text = note.title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
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

        FloatingActionButton(
            onClick = { navController.navigate(Screen.AddNote.route) },
            modifier = Modifier
                .align(Alignment.End)
                .padding(10.dp)
                .background(Color.Transparent),
            containerColor = Color.White
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "")
        }
    }
}



