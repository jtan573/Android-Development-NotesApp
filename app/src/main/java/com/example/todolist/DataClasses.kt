package com.example.todolist

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.util.UUID

data class Task(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String,
    var isChecked: MutableState<Boolean> = mutableStateOf(false)
)

sealed class Screen(val route:String) {
    object HomeView : Screen("home_view")
    object NewTaskView : Screen("new_task_view")
}