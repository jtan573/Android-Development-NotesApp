package com.example.todolist.ui.theme

sealed class Screen(val route:String) {
    object HomeView : Screen("home_view")
    object NewTaskView : Screen("new_task_view")
}
