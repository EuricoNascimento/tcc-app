package com.github.edu.look.repository.remote

import com.github.edu.look.data.Course
import com.github.edu.look.data.materials.ClassMaterial
import com.github.edu.look.data.materials.MaterialTopic
import com.github.edu.look.data.student.StudentProfile
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface EduLookService {
    @GET("/v1/students/profile")
    suspend fun getUserProfile(@Header("Authorization") token: String): StudentProfile

    @GET("/v1/courses")
    suspend fun getCourses(@Header("Authorization") token: String): List<Course>

    @GET("/v1/courses/{courseId}/materials")
    suspend fun getMaterialTopics(
        @Header("Authorization") token: String,
        @Path("courseId") courseId: String
    ): List<MaterialTopic>

    @GET("/v1/courses/{courseId}/materials/{materialId}")
    suspend fun getClassMaterial(
        @Header("Authorization") token: String,
        @Path("courseId") courseId: String,
        @Path("materialId") materialId: String
    ): ClassMaterial
}