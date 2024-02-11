package com.github.edu.look.repository

import android.util.Log
import com.github.edu.look.data.student.StudentProfile
import com.github.edu.look.repository.local.LookDataBase
import com.github.edu.look.repository.local.StudentsProfileDao
import com.github.edu.look.repository.remote.EduLookService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class LoginRepository @Inject constructor(
    val eduLookService: EduLookService
) {
    fun saveToken(tokenId: String): Flow<Boolean> = flow {

    }
}