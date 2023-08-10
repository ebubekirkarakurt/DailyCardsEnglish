package com.ekasoftware.english.view.mynotes.ui.AddNote

import android.app.Application
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
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.ekasoftware.english.assets.Screen
import com.ekasoftware.english.ui.theme.CorrectColor
import com.ekasoftware.english.view.mynotes.data.Note
import com.ekasoftware.english.view.mynotes.model.NoteDatabase
import com.ekasoftware.english.view.mynotes.repository.NoteRepository
import com.ekasoftware.english.view.mynotes.viewmodel.NoteViewModelProviderFactory
import com.ekasoftware.english.view.mynotes.viewmodel.NotesViewModel

@Composable
fun AddNote(navController: NavHostController) {
    AddNoteItems(navController)
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteItems(navController: NavHostController) {

    var title by remember { mutableStateOf("") }
    var comment by remember { mutableStateOf("") }

    val noteRepository = NoteRepository(NoteDatabase(LocalContext.current.applicationContext))
    val viewModel: NotesViewModel = viewModel(
        factory = NoteViewModelProviderFactory(app = LocalContext.current.applicationContext
                as Application,
            noteRepository = noteRepository)
    )


    Column(modifier = Modifier.fillMaxSize()){

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween){
            Button(
                modifier = Modifier.background(Color.Transparent),
                onClick = { navController.navigate(Screen.Home.route) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black
                )

            ) {

                Icon(imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = null,
                )
            }

            Button(
                modifier = Modifier.background(Color.White),
                onClick = {
                    val note = Note(
                        0,
                        title,
                        comment
                    )
                    if(title.isNotEmpty()){
                        viewModel.addNote(note)
                        navController.popBackStack()}
                    },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = CorrectColor
                )

            ) {

                Icon(imageVector = Icons.Filled.Check,
                    contentDescription = null,
                )
            }
        }


        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
        ){
            Column {

                TextField(
                    value = title,
                    onValueChange = {title = it},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Black,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    placeholder = { Text(text = "Not başlığı girin")},
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                )

                TextField(
                    value = comment,
                    onValueChange = {comment = it},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        textColor = Color.Black,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    ),
                    placeholder = { Text(text = "Not açıklaması girin")},
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize(),
                    maxLines = 20
                )

            }

        }
    }
}



