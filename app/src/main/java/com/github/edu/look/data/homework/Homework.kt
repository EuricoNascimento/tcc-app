package com.github.edu.look.data.homework

data class Homework(
    val id: Long = 0L,
    val homeworkName: String = "",
    val date: String = "",
    val question: List<Question> = listOf()
)
