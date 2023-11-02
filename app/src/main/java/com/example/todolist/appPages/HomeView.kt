package com.example.todolist.appPages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todolist.Note
import com.example.todolist.Screen

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
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.NewNoteView.route)
                        }
                    ) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "create_icon",
                            tint = Color.White
                        )
                    }
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
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(top = 8.dp))
            ListView(list = list, navController = navController)
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
    TextButton(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = {
            navController.navigate(Screen.NoteView.withArgs(note.id))
        },
        shape = RectangleShape,
        contentPadding = PaddingValues(10.dp)
    ) {
        Text(
            text = note.title,
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp)
                .fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            textAlign = TextAlign.Left
        )
    }
}