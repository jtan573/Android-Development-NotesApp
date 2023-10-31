package com.example.todolist.appPages

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolist.Task
import com.example.todolist.Screen

@Composable
fun Navigation (list: MutableList<Task>) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.HomeView.route) {
        composable (route = Screen.HomeView.route) {
            HomeView(list = list, navController = navController)
        }
        composable(route = Screen.NewTaskView.route)
        {
            NewTaskView(list = list, navController = navController)
        }
    }
}
