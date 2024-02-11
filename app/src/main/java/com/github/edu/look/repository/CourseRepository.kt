package com.github.edu.look.repository

import android.content.Context
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.edu.look.data.Course
import com.github.edu.look.repository.local.SessionManager
import com.github.edu.look.repository.remote.EduLookService
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class CourseRepository @Inject constructor(
    @ApplicationContext context: Context,
    val eduLookService: EduLookService
) {
    var sessionManager = SessionManager(context)
    suspend fun getCourses(): List<Course> {
        val token = sessionManager.fetchAuthToken() ?: return listOf()

        return eduLookService.getCourses(token)
    }
}