package com.github.edu.look.repository

import android.content.Context
import com.github.edu.look.data.Course
import com.github.edu.look.repository.local.SessionManager
import com.github.edu.look.repository.remote.EduLookService
import com.github.edu.look.repository.remote.network.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CourseRepository @Inject constructor(
    context: Context,
    val eduLookService: EduLookService
) {
    var sessionManager = SessionManager(context)
    suspend fun getCourses(): Flow<ApiResult<List<Course>>> = flow {
        emit(ApiResult.Loading)
        val token = sessionManager.fetchAuthToken()
        val responseCourse = eduLookService.getCourses("Bearer $token")
        emit(ApiResult.Success(responseCourse))
    }.flowOn(Dispatchers.IO)
}