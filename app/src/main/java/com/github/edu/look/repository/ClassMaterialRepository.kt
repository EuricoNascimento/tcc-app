package com.github.edu.look.repository

import android.content.Context
import com.github.edu.look.data.Course
import com.github.edu.look.data.materials.ClassMaterial
import com.github.edu.look.data.materials.MaterialTopic
import com.github.edu.look.repository.local.SessionManager
import com.github.edu.look.repository.remote.EduLookService
import com.github.edu.look.repository.remote.network.ApiResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ClassMaterialRepository @Inject constructor(
    val eduLookService: EduLookService,
    val sessionManager: SessionManager
) {
    suspend fun getClassMaterial(courseId: String, materialId: String)
    : Flow<ApiResult<ClassMaterial>> = flow {
        emit(ApiResult.Loading)
        val token = "Bearer ${sessionManager.fetchAuthToken()}"
        val responseCourse = eduLookService.getClassMaterial(token, courseId, materialId)
        emit(ApiResult.Success(responseCourse))
    }.flowOn(Dispatchers.IO)

    suspend fun getMaterialTopics(courseId: String)
            : Flow<ApiResult<List<MaterialTopic>>> = flow {
        emit(ApiResult.Loading)
        val token = "Bearer ${sessionManager.fetchAuthToken()}"
        val responseCourse = eduLookService.getMaterialTopics(token, courseId)
        emit(ApiResult.Success(responseCourse))
    }.flowOn(Dispatchers.IO)
}