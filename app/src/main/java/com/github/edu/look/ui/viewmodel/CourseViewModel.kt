package com.github.edu.look.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.edu.look.data.Course
import com.github.edu.look.repository.CourseRepository
import com.github.edu.look.repository.remote.network.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CourseViewModel @Inject constructor(
    private val courseRepository: CourseRepository
): ViewModel() {
    private var _coursesState = MutableStateFlow(listOf<Course>())
    val coursesState get() = _coursesState.asStateFlow()

    fun getCourses() = viewModelScope.launch {
        courseRepository.getCourses().collect { result ->
            when (result) {
                is ApiResult.Success -> {
                    _coursesState.value = result.data
                }
                else -> {
                    _coursesState.value = listOf()
                }
            }
        }
    }
}