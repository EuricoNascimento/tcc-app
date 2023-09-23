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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.edu.look.R
import com.github.edu.look.data.classtopic.ClassTopic
import com.github.edu.look.ui.component.TopicCard
import com.github.edu.look.ui.theme.LookDefault
import com.github.edu.look.ui.theme.LookTheme
import com.github.edu.look.ui.viewmodel.classtopic.ClassTopicViewModel

@Composable
fun ClassTopicScreen(
    classTopicViewModel: ClassTopicViewModel = viewModel(),
) {
    val topics by classTopicViewModel.uiState.collectAsState()
    ClassTopic(topics)
}

@Composable
fun ClassTopic(topics: List<ClassTopic>, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxSize()
    ) {
        LazyColumn {
            items(items = topics) { item ->
                TopicCard(
                    title = item.topic,
                    subTitle = stringResource(id = R.string.posted, item.date),
                    border = BorderStroke(LookDefault.Stroke.small, MaterialTheme.colorScheme.onPrimary)
                )
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewTeste() {
    LookTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ClassTopic(mutableListOf(
                ClassTopic(id = 1, topic = "Tabela periodica parte 1", date = "20/03/2023"),
                ClassTopic(id = 2, topic = "Tabela periodica parte 2", date = "21/03/2023")
            ))
        }
    }
}