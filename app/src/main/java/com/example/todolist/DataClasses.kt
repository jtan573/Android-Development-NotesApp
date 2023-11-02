package com.example.todolist

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.util.UUID

data class Note(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String,
    var isChecked: MutableState<Boolean> = mutableStateOf(false)
)

sealed class Screen(val route:String) {
    object HomeView : Screen("home_view")
    object NewNoteView : Screen("new_note_view")
    object NoteView : Screen("note_view")
    object EditNoteView : Screen("edit_note_view")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}