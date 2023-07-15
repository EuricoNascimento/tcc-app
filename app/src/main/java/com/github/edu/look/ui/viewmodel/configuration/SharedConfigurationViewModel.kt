package com.github.edu.look.ui.viewmodel.configuration

import android.app.Application
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class SharedConfigurationViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val context: Application
) : ViewModel() {

    val fontScale get() = fontSizeScale.asStateFlow()

    fun updateFontScale(scale: TextUnit) = fontSizeScale.update { scale }

    companion object {
        private var fontSizeScale: MutableStateFlow<TextUnit> = MutableStateFlow(1.sp)
    }
}