package com.example.todolist

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import java.util.UUID

data class Note(
    val id: String = UUID.randomUUID().toString(),
    var title: String,
    var description: String,
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
