package com.github.dannrocha.look.ui.presentation.configuration

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.dannrocha.look.ui.viewmodel.configuration.SharedConfigurationViewModel

@Composable
fun ConfigurationApresentation(
    sharedConfigurationViewModel: SharedConfigurationViewModel
) {
    val setupList = listOf(
        "Tamanho das letras",
        "Cores e fundo"
    )

    val fontScale by sharedConfigurationViewModel.fontScale.collectAsState(initial = 1.sp)

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            SetupItem(label = "Tamanho da letras", fontScale=fontScale) {
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
fun SetupItem(label: String, modifier: Modifier = Modifier, fontScale: TextUnit = 1.sp, content: @Composable() (ColumnScope.() -> Unit)) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            Text(text = label, fontSize = 30.times(fontScale.value).sp)
            content()
        }
    }
}
