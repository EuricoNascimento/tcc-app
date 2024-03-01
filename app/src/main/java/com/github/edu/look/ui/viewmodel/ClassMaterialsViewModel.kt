package com.github.edu.look.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.edu.look.data.materials.ClassMaterial
import com.github.edu.look.data.materials.Material
import com.github.edu.look.repository.ClassMaterialRepository
import com.github.edu.look.repository.remote.network.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClassMaterialsViewModel @Inject constructor(
    private val classMaterialRepository: ClassMaterialRepository
): ViewModel() {
    private val _currentMaterial = MutableStateFlow(Material())
    val currentMaterial get() = _currentMaterial.asStateFlow()
    private val materialIdStack: MutableList<String> = mutableListOf()
    private var _materialState = MutableStateFlow(listOf<Material>())
    private var _classMaterial: MutableStateFlow<ClassMaterial> = MutableStateFlow(ClassMaterial())
    val classMaterial get() = _classMaterial.asStateFlow()

    var isStartMaterial: Boolean = false
    
    fun getClassMaterials(courseId: String, materialId: String) = viewModelScope.launch {
        classMaterialRepository.getClassMaterial(courseId, materialId).collect { result ->
            when (result) {
                is ApiResult.Success -> {
                    _classMaterial.value = result.data
                    _materialState.value = result.data.materials
                    getCurrentMaterial(Navigation.START)
                }
                else -> {}
            }
        }
    }

    fun getCurrentMaterial(navigation: Navigation){
        when (navigation) {
            Navigation.START -> {
                _currentMaterial.value = _materialState.value.first()
                materialIdStack.add(_currentMaterial.value.id)
            }
            Navigation.NEXT -> {
                if (materialIdStack.size > 1) {
                    _currentMaterial.value = _materialState.value
                        .first { !materialIdStack.contains(it.id) }
                    materialIdStack.add(_currentMaterial.value.id)
                }
            }
            Navigation.BACK -> {
                if (materialIdStack.size > 1) {
                    val materialId = materialIdStack.removeLast()
                    _currentMaterial.value = _materialState.value.first { it.id == materialId }
                }
            }
        }
    }

    enum class Navigation {
        START,
        NEXT,
        BACK;
    }
}