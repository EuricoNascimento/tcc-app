package com.github.edu.look.ui.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

import com.github.edu.look.R

import com.github.edu.look.data.communication.Communication
import com.github.edu.look.ui.component.TopicCard
import com.github.edu.look.ui.theme.LookDefault

import com.github.edu.look.ui.viewmodel.classtopic.CommunicationViewModel
import kotlinx.coroutines.flow.MutableStateFlow


@Composable
fun CommunicationPresentation(
    classTopicViewModel: CommunicationViewModel = viewModel()
) {
    val communication by classTopicViewModel.uiState.collectAsState()
   CommunicationItems(communication)

}

@Composable
fun CommunicationItems(communication: List<Communication>, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxSize()
    ) {
        LazyColumn {
            items(items = communication) { item ->
                TopicCard(
                    title = item.content,
                    subTitle = stringResource(id = R.string.posted, item.publicationDate),
                    border = BorderStroke(LookDefault.Stroke.small, MaterialTheme.colorScheme.onPrimary)
                )
            }
        }
    }
}







