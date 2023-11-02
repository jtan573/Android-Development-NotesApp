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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todolist.Note
import com.example.todolist.Screen
import com.example.todolist.ui.theme.Purple40
import com.example.todolist.ui.theme.Purple80
import com.example.todolist.ui.theme.PurpleGrey40
import com.example.todolist.ui.theme.PurpleGrey80

@Composable
fun HomeView (list: MutableList<Note>, navController: NavController) {
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(modifier = Modifier.padding(10.dp),
                        text = "My TODO List",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                backgroundColor = Color.DarkGray
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
            Spacer(modifier = Modifier.padding(top = 8.dp))
            ListView(list = list, navController = navController)
            Spacer(modifier = Modifier.padding(top = 8.dp))
            Button(
                modifier = Modifier.padding(8.dp),
                onClick = { navController.navigate(Screen.NewNoteView.route) }
            ) {
                Text(text = "ADD NOTE")
            }
        }
    }
}

@Composable
fun ListView(list: MutableList<Note>, navController: NavController) {
    LazyColumn {
        items(list) { note ->
            TaskRow(note, list, navController)
        }
    }
}

@Composable
fun TaskRow(note: Note, list: MutableList<Note>, navController: NavController) {
    Row (modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        TextButton(
            modifier = Modifier.padding(start = 5.dp),
            onClick = {
                navController.navigate(
                    Screen.NoteView.withArgs(note.id)
                )
            }
        ) {
            Text(
                text = note.title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            modifier = Modifier.absolutePadding(right = 5.dp),
            onClick = { list.removeAt(list.indexOfFirst { it.title == note.title }) }
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete"
            )
        }
    }
}