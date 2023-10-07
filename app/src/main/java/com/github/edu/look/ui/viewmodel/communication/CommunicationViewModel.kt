package com.github.edu.look.ui.viewmodel.classtopic

import androidx.lifecycle.ViewModel
import com.github.edu.look.data.communication.Communication
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CommunicationViewModel: ViewModel() {

    private var _uiState =  MutableStateFlow(mutableListOf<Communication>())
    val uiState get() = _uiState.asStateFlow()

    init {
        _uiState = MutableStateFlow(mutableListOf(
            Communication(id = 1,  publicationDate = "20/03/2023", content = "Tabela periodica parte 1"),
            Communication(id = 2,  publicationDate = "20/03/2023", content = "Tabela periodica parte 2")
        ))
    }
}