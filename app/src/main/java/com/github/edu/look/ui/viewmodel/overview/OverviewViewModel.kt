package com.github.edu.look.ui.viewmodel.overview

import androidx.lifecycle.ViewModel
import com.github.edu.look.data.classroom.Classroom
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OverviewViewModel : ViewModel() {
    private var _uiState =  MutableStateFlow(mutableListOf<Classroom>())
    val uiState get() = _uiState.asStateFlow()

    init {
        _uiState = MutableStateFlow(mutableListOf<Classroom>(
            Classroom(id = "1", name = "Sociologia", teacherName = "Professor Astrogildo"),
            Classroom(id = "2", name = "Biologia", teacherName = "Professor Marciano")
        ))
    }

}