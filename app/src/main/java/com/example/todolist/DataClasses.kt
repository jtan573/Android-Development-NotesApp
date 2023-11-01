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
    object TaskView : Screen("task_view")
    object EditTaskView : Screen("edit_task_view")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}