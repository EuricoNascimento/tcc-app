package com.github.edu.look.data


import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class Course(
    @Json(name = "id") val id: String? = "",
    @Json(name = "description") val description: String? = "",
    @Json(name = "name") val name: String? = "",
    @Json(name = "owner") val owner: String? = "",
    @Json(name = "room") val room: String? = "",
    @Json(name = "state") val state: String? = "",
    @Json(name = "teachers") val teachers: List<Teacher>? = listOf()
)