package com.github.edu.look.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.github.edu.look.data.classroom.Classroom
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CourseViewModel(): ViewModel() {
    private var _uiState =  MutableStateFlow(mutableListOf<Classroom>())
    val uiState get() = _uiState.asStateFlow()

    init {
        _uiState = MutableStateFlow(mutableListOf(
            Classroom(id = 1L, name = "Historia", teacherName = "Gentil"),
            Classroom(id = 2L, name = "Teste 2", teacherName = "Luna")
        ))
    }
}