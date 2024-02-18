package com.github.edu.look.data.student

import com.squareup.moshi.Json

class StudentProfile(
    @Json(name = "name") var name: String,
    @Json(name = "email") var email: String,
    @Json(name = "photo") var photo: String
)
