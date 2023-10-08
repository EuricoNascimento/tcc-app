package com.github.edu.look.ui.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.github.edu.look.R
import com.github.edu.look.ui.component.ScaleText
import com.github.edu.look.ui.theme.LookDefault
import com.github.edu.look.ui.viewmodel.homework.HomeworkQuestionViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.edu.look.data.homework.Question
import com.github.edu.look.ui.component.TopicCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeworkAnswersPresentation(
    navController: NavController,
    viewModel: HomeworkQuestionViewModel = viewModel()
) {

    Scaffold (
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .defaultMinSize(minHeight = LookDefault.Padding.ultraLarge)
                    .background(color = MaterialTheme.colorScheme.onPrimary)
                    .clickable { navController.navigate(RouterSet.ClassTopicPresentation.name) }
            ) {
                ScaleText(
                    text = stringResource(R.string.send_homework),
                    fontSize = LookDefault.FontSize.large,
                    color = MaterialTheme.colorScheme.secondary,
                    )
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding)
        ) {
            items(items = viewModel.getQuestionWithAnswer()) {
                ScaleText(
                    text = it.first.question,
                    fontSize = LookDefault.FontSize.small,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(all = LookDefault.Padding.small)
                )
                TopicCard(
                    title = it.second,
                    contentModifier = Modifier.size(size = LookDefault.Size.normal),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    border = BorderStroke(
                        width = LookDefault.Stroke.small,
                        color = MaterialTheme.colorScheme.onPrimary
                    ),
                    colorTitle = MaterialTheme.colorScheme.onPrimary,
                    onClick = { navController.navigate(RouterSet.HomeworkQuestionPresentation.name) }
                )
            }
        }
    }
}