package com.github.edu.look.ui.viewmodel.classtopic

import androidx.lifecycle.ViewModel
import com.github.edu.look.data.classtopic.ClassTopic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ClassTopicViewModel @Inject constructor(): ViewModel() {
    private var _uiState =  MutableStateFlow(mutableListOf<ClassTopic>())
    val uiState get() = _uiState.asStateFlow()

    init {
        _uiState = MutableStateFlow(mutableListOf(
            ClassTopic(id = 0, topic = "Tabela periodica parte 1", date = "20/03/2023"),
            ClassTopic(id = 1, topic = "Tabela periodica parte 2", date = "21/03/2023")
        ))
    }
}