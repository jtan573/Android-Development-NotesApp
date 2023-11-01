package com.example.todolist.appPages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
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
import com.example.todolist.Task
import com.example.todolist.Screen

// Add New Task
@Composable
fun NewTaskView(list: MutableList<Task>, navController: NavController) {
    var titleInput by rememberSaveable { mutableStateOf(value = "") }
    var descInput by rememberSaveable { mutableStateOf(value = "") }

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(
                    text = "Create New Task",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )},
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.TaskView.route)
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
                value = titleInput,
                onValueChange = { titleInput = it },
                label = { Text("Title") }
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                value = descInput,
                onValueChange = { descInput = it },
                label = { Text(text = "Description") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Add error checking for notes input

            Button(
                modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                onClick = {
                    list.add(Task(title = titleInput, description = descInput))
                    titleInput = ""
                    descInput = ""
                    navController.navigate(Screen.HomeView.route)
                }
            ) {
                Text(text = "ADD TO LIST")
            }
        }
    }
}