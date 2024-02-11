package com.github.edu.look.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.edu.look.data.Course
import com.github.edu.look.data.classroom.Classroom
import com.github.edu.look.repository.CourseRepository
import com.github.edu.look.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseViewModel @Inject constructor(
    private val courseRepository: CourseRepository
): ViewModel() {
    private var _uiState =  MutableStateFlow(listOf<Course>())
    val uiState get() = _uiState.asStateFlow()

    fun getCourses() {
        viewModelScope.launch {
            val courses = courseRepository.getCourses()
            if (courses.isNotEmpty()) {
                _uiState.value = courses
            } else {
                _uiState.value = listOf(
                    Course(
                        name = "Error ao acessar a sala"
                    )
                )
            }
        }
    }
}