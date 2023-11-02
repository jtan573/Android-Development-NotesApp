package com.example.todolist.appPages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todolist.Note
import com.example.todolist.Screen

@Composable
fun EditNoteView(noteId: String?, list: MutableList<Note>, navController: NavController) {
    var targetNote: Note? = list.find { it.id == "$noteId" }
    var noteTitle by rememberSaveable { mutableStateOf(value = targetNote?.title) }
    var noteDesc by rememberSaveable { mutableStateOf(value = targetNote?.description) }

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(
                    text = "Edit Current Note",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.NoteView.route)
                        }
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "back_icon"
                        )
                    }
                },
                backgroundColor = Color.DarkGray
            )
        }
    ) { paddingValues ->
        // Content of Scaffold
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                value = "$noteTitle",
                onValueChange = { noteTitle = it },
                label = { Text("Title") }
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                value = "$noteDesc",
                onValueChange = { noteDesc = it },
                label = { Text(text = "Description") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Add error checking for notes input

            Button(
                modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                onClick = {
                    list.add(Note(title = "$noteTitle", description = "$noteDesc"))
                    noteTitle = ""
                    noteDesc = ""
                    navController.navigate(Screen.HomeView.route)
                }
            ) {
                Text(text = "ADD TO LIST")
            }
        }
    }
}