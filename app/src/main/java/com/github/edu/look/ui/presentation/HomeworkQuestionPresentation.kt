package com.github.edu.look.ui.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.github.edu.look.R
import com.github.edu.look.data.homework.Question
import com.github.edu.look.ui.component.ScaleText
import com.github.edu.look.ui.component.TopicCard
import com.github.edu.look.ui.theme.LookDefault
import com.github.edu.look.ui.viewmodel.homework.HomeworkQuestionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeworkQuestionPresentation(
    viewModel: HomeworkQuestionViewModel = viewModel(),
    navController: NavController,
    questionId: Long?,
    homeworkId: Long?,
    classroomId: Long?
) {

    val homework by viewModel.uiState.collectAsState()
    val question = viewModel.getQuestion(questionId ?: 0L)

    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .defaultMinSize(minHeight = LookDefault.Padding.ultraLarge)
                    .background(color = MaterialTheme.colorScheme.onPrimary)
                    .clickable {
                        if (viewModel.isLastQuestion(question)) {
                            navController.navigate(RouterSet.HomeworkAnswersPresentation.name)
                        } else {
                            val route ="${RouterSet.HomeworkQuestionPresentation.name}" +
                                    "/$classroomId/$homeworkId?questionId=${viewModel.getNextQuestion(question)}"
                            navController.navigate(route)
                        }
                    }
            ) {
                val bottomResource = if (viewModel.isLastQuestion(question)) {
                    R.string.finish
                } else {
                    R.string.next
                }
                ScaleText(
                    text = stringResource(bottomResource),
                    fontSize = LookDefault.FontSize.large,
                    color = MaterialTheme.colorScheme.secondary,
                )
            }
        }
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(MaterialTheme.colorScheme.secondary)
        ) {
            ScaleText(
                text = homework.homeworkName,
                fontSize = LookDefault.FontSize.large,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(all = LookDefault.Padding.large)
            )
            Spacer(modifier = Modifier.padding(vertical = LookDefault.Padding.extraLarge))
            ScaleText(
                text = stringResource(id = R.string.posted, homework.date),
                fontSize = LookDefault.FontSize.medium,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(all = LookDefault.Padding.large)
            )
            Spacer(modifier = Modifier.padding(vertical =  LookDefault.Padding.middle))
            SelectedOption(question = question, viewModel = viewModel)
        }
    }

}

@Composable
private fun SelectedOption(question: Question, viewModel: HomeworkQuestionViewModel) {
    var selected by remember{ mutableStateOf(-1) }

    Column {
        ScaleText(
            text = stringResource(R.string.question,  question.question),
            fontSize = LookDefault.FontSize.medium,
            color = MaterialTheme.colorScheme.onPrimary
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(all = LookDefault.Padding.large)
        ) {
            items(count = question.option.size) { index ->
                TopicCard(
                    title = question.option[index],
                    colors = CardDefaults.cardColors(
                        containerColor = if(index == selected) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.secondary
                    ),
                    border = BorderStroke(LookDefault.Stroke.small, MaterialTheme.colorScheme.onPrimary),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    onClick = {
                        selected = index
                        viewModel.saveAnswer(question.id, question.option[index])
                    }
                )
            }
        }
    }
}