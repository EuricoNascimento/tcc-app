package com.github.edu.look.data.homework

data class Question (
    val id: Long = 0L,
    val question: String = "",
    val option: List<Option> = listOf()
)