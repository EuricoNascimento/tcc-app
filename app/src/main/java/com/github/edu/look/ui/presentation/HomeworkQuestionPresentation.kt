package com.github.edu.look.ui.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.edu.look.R
import com.github.edu.look.data.homework.Question
import com.github.edu.look.ui.component.ScaleText
import com.github.edu.look.ui.component.TopicCard
import com.github.edu.look.ui.theme.LookDefault
import com.github.edu.look.ui.viewmodel.homework.HomeworkViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeworkQuestionPresentation(
    viewModel: HomeworkViewModel,
    navController: NavController,
    questionId: Long?,
    homeworkId: Long?,
    classroomId: Long?,
    answer: String?
) {

    val homework by viewModel.uiState.collectAsState()
    val question = viewModel.getQuestion(questionId ?: 0L)
    viewModel.classroomId = classroomId
    viewModel.homeworkId = homeworkId

    Scaffold(
        bottomBar = {
            val bottomResource = if (viewModel.isLastQuestion(question) || !answer.isNullOrEmpty()) {
                R.string.finish
            } else {
                R.string.next
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = LookDefault.Padding.ultraLarge)
                    .clip(
                        RoundedCornerShape(
                            topEnd = LookDefault.Padding.extraLarge,
                            topStart = LookDefault.Padding.extraLarge
                        )
                    )
                    .clickable {
                        if (viewModel.isLastQuestion(question) || !answer.isNullOrEmpty()) {
                            navController.navigate(RouterSet.HomeworkAnswersPresentation.name)
                        } else if (viewModel.wasAnsweredQuestion(question.id)) {
                            viewModel.questionNumber++
                            navController.navigate(
                                RouterSet.HomeworkQuestionPresentation.name +
                                        "/$classroomId/$homeworkId" +
                                        "?questionId=${viewModel.getNextQuestion(question)}" +
                                        "?answer=$answer"
                            )
                        }
                    }
                    .background(color = MaterialTheme.colorScheme.onPrimary),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ScaleText(
                    text = stringResource(bottomResource),
                    fontSize = LookDefault.FontSize.large,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .padding(all = LookDefault.Padding.extraLarge)
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.secondary
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
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(all = LookDefault.Padding.large)
            )
            Spacer(modifier = Modifier.padding(vertical = LookDefault.Padding.small))
            ScaleText(
                text = stringResource(id = R.string.posted, homework.date),
                fontSize = LookDefault.FontSize.medium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(all = LookDefault.Padding.large)
            )
            Spacer(modifier = Modifier.padding(vertical =  LookDefault.Padding.middle))
            SelectedOption(question = question, viewModel = viewModel, answer = answer)
        }
    }

}

@Composable
private fun SelectedOption(question: Question, viewModel: HomeworkViewModel, answer: String?) {
    var selected by remember{ mutableStateOf(-1) }
    var selectedAnswer by remember {
        mutableStateOf(
            if (answer.isNullOrEmpty()) {
                null
            } else {
                answer
            }
        )
    }

    ScaleText(
        text = stringResource(R.string.question,  viewModel.questionNumber, question.question),
        fontSize = LookDefault.FontSize.medium,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onPrimary
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 2),
        modifier = Modifier
            .fillMaxSize()
            .padding(all = LookDefault.Padding.large)
    ) {
        items(count = question.option.size) { index ->
            if (selectedAnswer != null && question.option[index] == selectedAnswer) {
                selected = index
                selectedAnswer = null
            }
            TopicCard(
                title = question.option[index],
                modifier = Modifier.height(height = LookDefault.Size.large),
                colorTitle = if (index == selected) MaterialTheme.colorScheme.secondary
                    else MaterialTheme.colorScheme.onPrimary,
                colors = CardDefaults.cardColors(
                    containerColor = if(index == selected) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.secondary
                ),
                border = BorderStroke(LookDefault.Stroke.small, MaterialTheme.colorScheme.onPrimary),
                verticalArrangement = Arrangement.Center,
                onClick = {
                    selected = index
                    viewModel.saveAnswer(question.id, question.option[index])
                }
            )
        }
    }
}