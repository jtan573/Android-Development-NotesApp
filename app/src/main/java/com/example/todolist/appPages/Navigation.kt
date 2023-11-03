package com.example.todolist.appPages

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todolist.Note
import com.example.todolist.Screen

@Composable
fun Navigation (list: MutableList<Note>) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeView.route) {
        // Home page
        composable (route = Screen.HomeView.route) {
            HomeView(list = list, navController = navController)
        }
        // Create New Note page
        composable(route = Screen.NewNoteView.route)
        {
            NewNoteView(list = list, navController = navController)
        }
        // View Note Details page
        composable(
            route = Screen.NoteView.route + "/{noteId}",
            arguments = listOf(
                navArgument("noteId") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) {entry ->
            NoteView(
                noteId = entry.arguments?.getString("noteId"),
                list = list,
                navController = navController
            )
        }
        // Edit Note Details page
        composable(
            route = Screen.EditNoteView.route + "/{noteId}",
            arguments = listOf(
                navArgument("noteId") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) { entry ->
            EditNoteView(
                noteId = entry.arguments?.getString("noteId"),
                list = list,
                navController = navController
            )
        }
    }
}
