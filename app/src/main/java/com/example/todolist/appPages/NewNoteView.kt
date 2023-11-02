package com.example.todolist.appPages

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

// Add New Task
@Composable
fun NewNoteView(list: MutableList<Note>, navController: NavController) {
    var titleInput by rememberSaveable { mutableStateOf(value = "") }
    var descInput by rememberSaveable { mutableStateOf(value = "") }
    var isErrorInTitle by rememberSaveable { mutableStateOf(false) }
    var isErrorInDesc by rememberSaveable { mutableStateOf(false) }

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(
                    text = "Create New Note",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )},
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
            OutlinedTextField (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                value = titleInput,
                onValueChange =
                {
                    titleInput = it
                    isErrorInTitle = isTitleError(titleInput, titleCharMax, titleCharMin)
                },
                label = { Text("Title") },
                supportingText = {
                    Column {
                        Text(text = "Count: ${titleInput.length}/$titleCharMax (Min 3 characters)")
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
                value = descInput,
                onValueChange =
                {
                    descInput = it
                    isErrorInDesc = isDescError(descInput, descCharMax)
                },
                label = { Text(text = "Description") },
                supportingText = {
                    Column {
                        Text(text = "Count: ${descInput.length}/$descCharMax")
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
            ) {
                Button(
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                    onClick = {
                        if (
                            (titleInput.length > titleCharMin) and
                            (titleInput.length <= titleCharMax) and
                            (descInput.length <= descCharMax)
                        ) {
                            list.add(Note(title = titleInput, description = descInput))
                            titleInput = ""
                            descInput = ""
                            navController.navigate(Screen.HomeView.route)
                        }
                    }
                ) {
                    Text(text = "ADD TO LIST")
                }
            }
        }
    }
}