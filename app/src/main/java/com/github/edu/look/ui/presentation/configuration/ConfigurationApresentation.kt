package com.github.edu.look.ui.presentation.configuration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.edu.look.R
import com.github.edu.look.ui.component.ScaleText
import com.github.edu.look.ui.theme.LookDefault
import com.github.edu.look.ui.viewmodel.configuration.SharedConfigurationViewModel

@Composable
fun ConfigurationPresentation(
    sharedConfigurationViewModel: SharedConfigurationViewModel = hiltViewModel()
) {

    val fontScale by sharedConfigurationViewModel.fontScale.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(vertical = LookDefault.Padding.extraLarge))
        Icon(
            imageVector = Icons.Filled.Person,
            contentDescription = stringResource(R.string.your_photo, "Fulano de Tal")
        )
        ScaleText(
            text = "Aluno Fulano de Tal",
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 22.sp)
        ScaleText(
            text = "20182SI0101",
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 18.sp)

        Spacer(modifier = Modifier.padding(vertical = LookDefault.Padding.middle))
        SetupItem(label = stringResource(id = R.string.size_text, fontScale.value)) {
            Slider(
                value = fontScale.value,
                onValueChange = {
                    sharedConfigurationViewModel.updateFontScale(it.sp)
                },
                valueRange = 0.5f..2f,
            )
        }
        Spacer(modifier = Modifier.padding(vertical = LookDefault.Padding.large))
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onPrimary),
            shape = MaterialTheme.shapes.medium
        ) {
            ScaleText(
                text = stringResource(R.string.exit_account),
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                fontSize = 22.sp
            )
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
        Column {
            ScaleText(text = label, fontSize = 12.sp, textAlign = TextAlign.Center)
            content()
        }
    }
}