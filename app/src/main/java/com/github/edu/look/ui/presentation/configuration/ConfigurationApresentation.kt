package com.github.edu.look.ui.presentation.configuration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.edu.look.ui.component.ScaleText
import com.github.edu.look.ui.theme.LookDefault
import com.github.edu.look.ui.viewmodel.configuration.SharedConfigurationViewModel

@Composable
fun ConfigurationPresentation(
    sharedConfigurationViewModel: SharedConfigurationViewModel = hiltViewModel()
) {

    val fontScale by sharedConfigurationViewModel.fontScale.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            SetupItem(label = "Tamanho das letras") {
                Slider(
                    value = fontScale.value,
                    onValueChange = {
                        sharedConfigurationViewModel.updateFontScale(it.sp)
                    },
                    valueRange = 0.5f..2f,
                )
            }
        }
    }
}

@Composable
fun SetupItem(
    label: String,
    modifier: Modifier = Modifier,
    content: @Composable() (ColumnScope.() -> Unit)
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 120.dp)
            .padding(LookDefault.Padding.middle),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            ScaleText(text = label, fontSize = 12.sp)
            content()
        }
    }
}
