package com.github.edu.look.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Teacher(
    @Json(name = "email") val email: String?,
    @Json(name = "id") val id: String?,
    @Json(name = "name") val name: String?,
    @Json(name = "photo") val photo: String?
)