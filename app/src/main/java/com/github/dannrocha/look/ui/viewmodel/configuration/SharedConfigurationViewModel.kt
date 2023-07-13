package com.github.dannrocha.look.ui.viewmodel.configuration

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// TODO: transforme to Singleton on DI
class SharedConfigurationViewModel : ViewModel() {
    private var _fontSizeScale: MutableStateFlow<TextUnit> = MutableStateFlow(1.sp)
    val fontScale get() = _fontSizeScale.asStateFlow()

    fun updateFontScale(scale: TextUnit) {
        _fontSizeScale.update { scale }
    }
}