package com.example.todolist.appPages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todolist.Task
import com.example.todolist.Screen

@Composable
fun HomeView (list: MutableList<Task>, navController: NavController) {
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Top App Bar") },
                navigationIcon = {
                    IconButton(
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "back_icon"
                        )
                    }
                }
            )
        }
    ){
        paddingValues ->
        // Content of Scaffold
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "My TODO List",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
            ListView(list = list)
            Button(
                modifier = Modifier.padding(8.dp),
                onClick = { navController.navigate(Screen.NewTaskView.route) }
            ) {
                Text(text = "ADD TASK")
            }
        }
    }
}

@Composable
fun ListView(list: MutableList<Task>) {
    LazyColumn {
        items(list) { task ->
            TaskRow(task, list)
        }
    }
}

@Composable
fun TaskRow(task: Task, list: MutableList<Task>) {
    Row (modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Checkbox(checked = task.isChecked.value,
            onCheckedChange = {task.isChecked.value = !task.isChecked.value}
        )
        Column {
            Text(text = task.title, fontWeight = FontWeight.Bold)
            Text(text = task.description)
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            modifier = Modifier.absolutePadding(right = 5.dp),
            onClick = { list.removeAt(list.indexOfFirst { it.title == task.title }) }
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete"
            )
        }
    }
}