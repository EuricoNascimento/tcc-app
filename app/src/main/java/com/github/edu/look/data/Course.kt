package com.github.edu.look.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Course(
    val description: String = "",
    val id: String = "",
    val name: String = "",
    val owner: String = "",
    val room: String = "",
    val state: String = "",
    val teachers: List<Teacher> = listOf()
)