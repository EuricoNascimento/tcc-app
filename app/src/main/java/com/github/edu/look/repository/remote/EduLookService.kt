package com.github.edu.look.repository.remote

import com.github.edu.look.data.Course
import com.github.edu.look.data.student.StudentProfile
import retrofit2.http.GET
import retrofit2.http.Header

interface EduLookService {
    @GET("/v1/students/profile")
    suspend fun getUserProfile(@Header("Authorization") token: String): StudentProfile

    @GET("v1/courses")
    suspend fun getCourses(@Header("Authorization") token: String): List<Course>
}