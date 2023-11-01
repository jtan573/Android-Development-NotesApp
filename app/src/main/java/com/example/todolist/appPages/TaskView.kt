package com.example.todolist.appPages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todolist.Screen
import com.example.todolist.Task

@Composable
fun TaskView(title: String?, description: String?, navController: NavController) {
    Column {
        Text(text = "$title")
        Text(text = "$description")
        Button(
            modifier = Modifier.padding(8.dp),
            onClick = { navController.navigate(Screen.EditTaskView.route) }
        ) {
            Text(text = "EDIT TASK")
        }
    }
}