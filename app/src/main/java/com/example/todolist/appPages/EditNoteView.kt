package com.example.todolist.appPages

import android.graphics.Paint.Align
import android.text.Layout.Alignment
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.example.todolist.descCharMax
import com.example.todolist.isDescError
import com.example.todolist.isTitleError
import com.example.todolist.titleCharMax
import com.example.todolist.titleCharMin

@Composable
fun EditNoteView(noteId: String?, list: MutableList<Note>, navController: NavController) {
    var targetNote: Note? = list.find { it.id == "$noteId" }
    var noteTitle by rememberSaveable { mutableStateOf(value = targetNote?.title) }
    var noteDesc by rememberSaveable { mutableStateOf(value = targetNote?.description) }

    var isErrorInTitle by rememberSaveable { mutableStateOf(false) }
    var isErrorInDesc by rememberSaveable { mutableStateOf(false) }

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Edit Current Note",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.NoteView.withArgs("$noteId"))
                        }
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "back_icon",
                            tint = Color.White
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
            Spacer(modifier = Modifier.height(5.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                value = "$noteTitle",
                onValueChange =
                {
                    noteTitle = it
                    isErrorInTitle = isTitleError(it, titleCharMax, titleCharMin)
                },
                label = { Text("Title") },
                supportingText = {
                    Column {
                        Text(text = "Count: ${"$noteTitle".length}/$titleCharMax (Min 3 characters)")
                        if (isErrorInTitle) {
                            Text(text = "Please enter a valid title.")
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.height(15.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                value = "$noteDesc",
                onValueChange =
                {
                    noteDesc = it
                    isErrorInDesc = isDescError(it, descCharMax)
                },
                label = { Text(text = "Description") },
                supportingText = {
                    Column {
                        Text(text = "Count: ${"$noteDesc".length}/$descCharMax")
                        if (isErrorInDesc) {
                            Text(text = "Please enter a valid description.")
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.height(15.dp))
            Row (
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center
            )
            {
                Button(
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                    onClick =
                    { if (
                        ("$noteTitle".length > titleCharMin) and
                        ("$noteTitle".length <= titleCharMax) and
                        ("$noteDesc".length <= descCharMax)
                    ) {
                        targetNote?.title = "$noteTitle"
                        targetNote?.description = "$noteDesc"
                        noteTitle = ""
                        noteDesc = ""
                        navController.navigate(Screen.NoteView.withArgs("$noteId"))
                    } }
                ) {
                    Text(text = "CONFIRM CHANGES")
                }
            }
        }
    }
}