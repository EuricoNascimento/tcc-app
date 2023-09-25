package com.github.edu.look.ui.presentation.configuration

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(vertical = LookDefault.Padding.ultraLarge))
        Icon(
            imageVector = Icons.Filled.Person,
            contentDescription = stringResource(R.string.your_photo, "Fulano de Tal"),
            modifier = Modifier
                .size(LookDefault.Padding.ultraLarge)
                .clip(CircleShape)
                .background(color = Color.Black)

        )
        ScaleText(
            text = "Aluno Fulano de Tal",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = LookDefault.FontSize.medium,
            fontWeight = FontWeight.Bold
        )
        ScaleText(
            text = "20182SI0101",
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = LookDefault.FontSize.medium
        )

        Spacer(modifier = Modifier.padding(vertical = LookDefault.Padding.middle))
        SetupItem(
            label = stringResource(id = R.string.size_text, fontScale.value)
            ) {
            Slider(
                value = fontScale.value,
                onValueChange = {
                    sharedConfigurationViewModel.updateFontScale(it.sp)
                },
                valueRange = 0.5f..2f,
                colors = SliderDefaults.colors(
                    thumbColor = MaterialTheme.colorScheme.onPrimary,
                    activeTrackColor = MaterialTheme.colorScheme.onPrimary,
                )
            )
        }
        Spacer(modifier = Modifier.padding(vertical = LookDefault.Padding.extraLarge))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = LookDefault.Padding.large),
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onPrimary),
            shape = MaterialTheme.shapes.extraLarge
        ) {
            ScaleText(
                text = stringResource(R.string.exit_account),
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                fontSize = LookDefault.FontSize.medium,
                fontWeight = FontWeight.Bold
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
            .padding(LookDefault.Padding.middle),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            ScaleText(
                text = label,
                fontSize = LookDefault.FontSize.small,
                color = MaterialTheme.colorScheme.onPrimary)
            content()
        }
    }
}