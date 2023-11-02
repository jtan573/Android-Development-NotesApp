package com.example.todolist.appPages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
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
import com.example.todolist.Screen
import com.example.todolist.Note

@Composable
fun NoteView(noteId: String?, list: MutableList<Note>, navController: NavController) {
    var targetNote: Note? = list.find { it.id == "$noteId" }
    var noteTitle by rememberSaveable { mutableStateOf(value = targetNote?.title) }
    var noteDesc by rememberSaveable { mutableStateOf(value = targetNote?.description) }

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(modifier = Modifier.padding(10.dp),
                        text = "$noteTitle",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.HomeView.route)
                        }
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "back_icon",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.EditNoteView.withArgs("$noteId"))
                        }
                    ) {
                        Icon(
                            Icons.Default.Create,
                            contentDescription = "edit_icon",
                            tint = Color.White
                        )
                    }
                    IconButton(
                        onClick = {
                            list.remove(targetNote)
                            navController.navigate(Screen.HomeView.route)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = Color.White
                        )
                    }
                },
                backgroundColor = Color.DarkGray
            )
        }
    ) { paddingValues ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){
            Row {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "Description:",
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = "$noteDesc"
            )
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}