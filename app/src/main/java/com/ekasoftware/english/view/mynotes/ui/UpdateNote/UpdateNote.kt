package com.ekasoftware.english.view.mynotes.ui.UpdateNote

import android.app.Application
import android.icu.text.CaseMap.Title
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.wear.compose.material.LocalContentColor
import com.ekasoftware.english.assets.Screen
import com.ekasoftware.english.ui.theme.CorrectColor
import com.ekasoftware.english.ui.theme.WrongColor
import com.ekasoftware.english.view.mynotes.data.Note
import com.ekasoftware.english.view.mynotes.model.NoteDatabase
import com.ekasoftware.english.view.mynotes.repository.NoteRepository
import com.ekasoftware.english.view.mynotes.viewmodel.NoteViewModelProviderFactory
import com.ekasoftware.english.view.mynotes.viewmodel.NotesViewModel

@Composable
fun UpdateNote(navController: NavHostController,
               id : Int,
               title : String,
               comment: String?

) {
    UpdateNoteItems(
        navController = navController,
        id = id,
        title = title,
        comment = comment
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateNoteItems(navController: NavHostController,
                    id : Int,
                    title : String,
                    comment: String?
) {

    var updatedTitle by remember { mutableStateOf(title) }
    var updatedComment by remember { mutableStateOf(comment) }

    val noteRepository = NoteRepository(NoteDatabase(LocalContext.current.applicationContext))
    val viewModel: NotesViewModel = viewModel(
        factory = NoteViewModelProviderFactory(app = LocalContext.current.applicationContext as Application, noteRepository = noteRepository)
    )

    Column(modifier = Modifier
        .padding(top = 15.dp)
        .fillMaxSize()
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                modifier = Modifier.background(Color.Transparent),
                onClick = { navController.navigate(Screen.Home.route) },
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = "",
                    tint = Color.Black
                )
            }
            Box(modifier = Modifier) {
                Row {
                    Button(
                        modifier = Modifier.background(Color.Transparent),
                        onClick = {
                            val updatedNote = Note(
                                id = id,
                                title = updatedTitle,
                                comment = updatedComment!!
                            )
                            viewModel.deleteNote(updatedNote)
                            navController.popBackStack()
                        },
                        colors = ButtonDefaults.buttonColors(Color.Transparent)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "",
                            tint = WrongColor
                        )
                    }
                    Button(
                        modifier = Modifier.background(Color.Transparent),
                        onClick = {
                            val updatedNote = Note(
                                id = id,
                                title = updatedTitle,
                                comment = updatedComment!!
                            )
                            viewModel.updateNote(updatedNote)
                            navController.popBackStack()
                        },
                        colors = ButtonDefaults.buttonColors(Color.Transparent)
                    ) {

                        Icon(

                            imageVector = Icons.Filled.Check,
                            contentDescription = "",
                            tint = CorrectColor
                        )
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Column {
                TextField(
                    value = updatedTitle,
                    onValueChange = { updatedTitle = it },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Black,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    placeholder = { Text(text = "Not başlığı girin") },
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                )

                TextField(
                    value = updatedComment!!,
                    onValueChange = { updatedComment = it },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Black,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    placeholder = { Text(text = "Not açıklaması girin") },
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    maxLines = 20
                )
            }
        }
    }
}

