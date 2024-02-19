package com.github.edu.look.ui.viewmodel.classtopic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.edu.look.data.classtopic.ClassTopic
import com.github.edu.look.data.enums.ClassType
import com.github.edu.look.data.materials.MaterialTopic
import com.github.edu.look.repository.ClassMaterialRepository
import com.github.edu.look.repository.remote.network.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClassTopicViewModel @Inject constructor(
    //val classMaterialRepository: ClassMaterialRepository
): ViewModel() {
    private var _uiState =  MutableStateFlow(mutableListOf<ClassTopic>())
    val uiState get() = _uiState.asStateFlow()
    private var _materialTopicsState =  MutableStateFlow(listOf<MaterialTopic>())
    val materialTopicsState get() = _materialTopicsState.asStateFlow()
    var classType: ClassType? = null

    init {
        _uiState = MutableStateFlow(mutableListOf(
            ClassTopic(id = 0, topic = "Tabela periodica parte 1", date = "20/03/2023"),
            ClassTopic(id = 1, topic = "Tabela periodica parte 2", date = "21/03/2023")
        ))
    }

    fun getClassTopic(courseId: String) = viewModelScope.launch {
        when (classType) {
            ClassType.CLASS_MATERIAL -> {}
            ClassType.COMMUNICATE -> {}
            else -> {}
        }
    }

//    private suspend fun getMaterialTopics(courseId: String) = viewModelScope.launch {
//       classMaterialRepository.getMaterialTopics(courseId).collect { result ->
//           when (result) {
//               is ApiResult.Success -> {
//                   _materialTopicsState.value = result.data
//                   _uiState.value = mutableListOf()
//               }
//
//               else -> {
//                   _materialTopicsState.value = listOf()
//                   _uiState.value = mutableListOf()
//               }
//           }
//       }
//    }
}