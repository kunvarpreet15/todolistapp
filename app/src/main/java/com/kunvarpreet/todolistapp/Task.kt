package com.kunvarpreet.todolistapp
data class Task(
    var title: String,
    var date: String = "",
    var time: String = "",
    var isDone: Boolean = false
)
