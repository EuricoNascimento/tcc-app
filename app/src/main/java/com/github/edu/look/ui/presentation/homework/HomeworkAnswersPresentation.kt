package com.github.edu.look.ui.presentation.homework

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.github.edu.look.R
import com.github.edu.look.ui.component.ScaleText
import com.github.edu.look.ui.theme.LookDefault
import com.github.edu.look.ui.viewmodel.homework.HomeworkViewModel
import com.github.edu.look.ui.component.TopicCard
import com.github.edu.look.ui.presentation.RouterSet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeworkAnswersPresentation(
    navController: NavController,
    viewModel: HomeworkViewModel
) {
    Scaffold (
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = LookDefault.Padding.ultraLarge)
                    .clip(
                        RoundedCornerShape(
                            topEnd = LookDefault.Padding.large,
                            topStart = LookDefault.Padding.large
                        )
                    )
                    .clickable {
                        navController.navigate(
                            RouterSet.ClassCoursePresentation.name
                                    + "/${viewModel.classroomId}"
                        )
                    }
                    .background(color = MaterialTheme.colorScheme.onPrimary)
                    .padding(all = LookDefault.Padding.extraLarge),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ScaleText(
                    text = stringResource(R.string.send_homework),
                    fontSize = LookDefault.FontSize.large,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.secondary
    ) { padding ->
        val items = viewModel.getQuestionWithAnswer()
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.secondary),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(count = items.size) {
                ScaleText(
                    text = stringResource(R.string.question, (it + 1), items[it].first.question ),
                    fontSize = LookDefault.FontSize.medium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(all = LookDefault.Padding.extraLarge)
                        .fillMaxWidth()
                )
                TopicCard(
                    title = items[it].second.label,
                    modifier = Modifier
                        .padding(horizontal = LookDefault.Padding.large)
                        .height(height = LookDefault.Size.large)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    border = BorderStroke(
                        width = LookDefault.Stroke.small,
                        color = MaterialTheme.colorScheme.onPrimary
                    ),
                    colorTitle = MaterialTheme.colorScheme.onPrimary,
                    onClick = {
                        viewModel.questionNumber = it + 1
                        navController.navigate(
                            RouterSet.HomeworkQuestionPresentation.name +
                                    "/${null}/${null}/${items[it].first.id}") {
                        }
                    }
                )
            }
        }
    }
}