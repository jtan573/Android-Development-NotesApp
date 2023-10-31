package com.example.todolist.appPages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todolist.Task
import com.example.todolist.Screen

// Add New Task
@Composable
fun NewTaskView(list: MutableList<Task>, navController: NavController) {
    var titleInput by rememberSaveable { mutableStateOf(value = "") }
    var descInput by rememberSaveable { mutableStateOf(value = "") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Text(text = "ADD NEW TASK")
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = titleInput,
            onValueChange = { titleInput = it },
            label = { Text("Title") }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = descInput,
            onValueChange = { descInput = it },
            label = { Text(text = "Description") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Add error checking for notes input

        Button(onClick = {
            list.add(Task(title = titleInput, description = descInput))
            titleInput = ""
            descInput = ""
            navController.navigate(Screen.HomeView.route)
        }) {
            Text(text = "ADD TO LIST")
        }
    }
}