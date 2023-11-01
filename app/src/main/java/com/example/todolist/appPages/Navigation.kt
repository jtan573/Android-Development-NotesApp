package com.example.todolist.appPages

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todolist.Task
import com.example.todolist.Screen

@Composable
fun Navigation (list: MutableList<Task>) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeView.route) {
        // Home page
        composable (route = Screen.HomeView.route) {
            HomeView(list = list, navController = navController)
        }
        // Create New Task page
        composable(route = Screen.NewTaskView.route)
        {
            NewTaskView(list = list, navController = navController)
        }
        // View Task Details page
        composable(
            route = Screen.TaskView.route + "/{title}/{description}",
            arguments = listOf(
                navArgument("title") {
                    type = NavType.StringType
                    defaultValue = "Untitled Task"
                    nullable = true
                },
                navArgument("description") {
                    type = NavType.StringType
                    defaultValue = "No Description"
                    nullable = true
                }
            )
        ) {entry ->
            TaskView(
                title = entry.arguments?.getString("title"),
                description = entry.arguments?.getString("description"),
                navController = navController
            )
        }
    }
}
