package com.example.todolist

// Functions to validate user input
val titleCharMin = 3
val titleCharMax = 50
val descCharMax = 120
fun isTitleError(input: String, max: Int, min: Int) : Boolean {
    return (input.length > max) or (input.length < min)
}
fun isDescError (input: String, max: Int) : Boolean {
    return (input.length > max)
}