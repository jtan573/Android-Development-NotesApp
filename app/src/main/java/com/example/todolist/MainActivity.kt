package com.example.todolist

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolist.ui.theme.TodoListTheme
import androidx.compose.material.*
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todolist.ui.theme.Screen
import java.time.format.TextStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
            val list = remember {
                mutableStateListOf(
                    Task(title = "Task 1", description = "Description of Task 1"),
                    Task(title = "Task 2", description = "Description of Task 2")
                )
            }
            TodoListTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    HomeView(list = list)
                }
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeView.route) {
        composable(
            route = Screen.HomeView.route,
            arguments = listOf(
                navArgument(name = "listOfTasks") {
                    type = mutableStateListOf<Task>(),

                }
            )
        ) {
            HomeView(list = list, navController = navController)
        }
        composable(
            route = Screen.NewTaskView.route,
            arguments = list
        )
    }
}

@Composable
fun HomeView (list: MutableList<Task>, navController: NavController) {
    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "My TODO List",
            modifier = Modifier.padding(10.dp),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        ListView(list = list)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(
            modifier = Modifier.align(alignment = Alignment.End),
            onClick = { navController.navigate(Screen.HomeView.route) }
        ) {
            Text(text = "Add task")
        }
        NewTaskView(list = list)
    }
}

// Add New Task
@Composable
fun NewTaskView(list: MutableList<Task>) {
    var titleInput by rememberSaveable { mutableStateOf(value = "") }
    var descInput by rememberSaveable { mutableStateOf(value = "") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
    ) {
        Text(text = "ADD NEW TASK")
        OutlinedTextField(
            value = titleInput,
            onValueChange = { titleInput = it },
            label = { Text("Title") }
        )
        OutlinedTextField(
            value = descInput,
            onValueChange = { descInput = it },
            label = { Text(text = "Description")}
        )
        Button(onClick = {
            list.add(Task(title = titleInput, description = descInput))
            titleInput = ""
            descInput = ""
        }) {
            Text(text = "ADD TO LIST")
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
        OutlinedButton(
            modifier = Modifier.absolutePadding(right = 5.dp),
            onClick = { list.removeAt(list.indexOfFirst { it.title == task.title }) },
            shape = CircleShape
        ) {
            // Icon(Icons.Default.Delete)
            Text(text = "Delete")
        }
    }
}
/*
@Preview(showBackground = true)
@Composable
fun RowViewPreview() {
    TodoListTheme {
        RowView(Task(title = "Hello", description = "World"))
    }
}
*/
