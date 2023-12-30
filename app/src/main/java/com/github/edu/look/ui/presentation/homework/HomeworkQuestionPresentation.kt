package com.github.edu.look.ui.presentation.homework

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.github.edu.look.R
import com.github.edu.look.data.homework.Option
import com.github.edu.look.data.homework.Question
import com.github.edu.look.ui.component.ScaleText
import com.github.edu.look.ui.component.TopicCard
import com.github.edu.look.ui.presentation.RouterSet
import com.github.edu.look.ui.theme.LookDefault
import com.github.edu.look.ui.viewmodel.homework.HomeworkQuestionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeworkQuestionPresentation(
    navController: NavController,
    homeworkId: Long?,
    classroomId: Long?,
    questionId: Long?,
    isEdit: String?,
    viewModel: HomeworkQuestionViewModel = hiltViewModel()
) {
    viewModel.startRequisition(classroomId, homeworkId)
    val homework by viewModel.uiState.collectAsState()
    val question by remember {
        viewModel.getFirstQuestion(questionId)
    }
    val answer = viewModel.getAnswer(question.id)

    Scaffold(
        bottomBar = {
            var bottomResource by remember {
                mutableStateOf(R.string.next)
            }
            if (viewModel.allQuestionAnswer()) {
                bottomResource = R.string.finish
            }
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
                        if (viewModel.allQuestionAnswer() || isEdit == "enable") {
                            if (isEdit == "disable") {
                                viewModel.saveAllAnswer()
                            } else {
                                viewModel.updateAnswer(questionId!!)
                            }
                            navController.navigate(
                                RouterSet.HomeworkAnswersPresentation.name
                                        + "/$classroomId/$homeworkId"
                            ) {
                                popUpTo(RouterSet.HomeworkQuestionPresentation.name +
                                        "?classroomId={classroomId}&topicId={topicId}" +
                                        "&questionId={questionId}&isEdit={isEdit}") {
                                    inclusive = true
                                }
                            }
                        } else if (viewModel.wasAnsweredQuestion(question.id)) {
                            viewModel.updateQuestion(null)
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
                .verticalScroll(rememberScrollState())
        ) {
            ScaleText(
                text = homework.homeworkName,
                fontSize = LookDefault.FontSize.large,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(all = LookDefault.Padding.extraLarge)
            )
            Spacer(modifier = Modifier.padding(vertical = LookDefault.Padding.small))
            ScaleText(
                text = stringResource(id = R.string.posted, homework.date),
                fontSize = LookDefault.FontSize.medium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(all = LookDefault.Padding.extraLarge)
            )
            Spacer(modifier = Modifier.padding(vertical =  LookDefault.Padding.middle))
            SelectedOption(question = question, viewModel = viewModel, option = answer)
        }
    }

}

@Composable
private fun SelectedOption(question: Question, viewModel: HomeworkQuestionViewModel, option: Option?) {
    var selected by remember{ mutableStateOf(-1L) }
    var selectedAnswer by remember { mutableStateOf( option?.id ) }

    val optionsGroup = question.option.chunked(2)

    ScaleText(
        text = stringResource(R.string.question,  viewModel.questionNumber, question.question),
        fontSize = LookDefault.FontSize.medium,
        textAlign = TextAlign.Justify,
        color = MaterialTheme.colorScheme.onPrimary,
        modifier = Modifier
            .padding(all = LookDefault.Padding.extraLarge)
            .fillMaxWidth()
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = LookDefault.Padding.extraLarge)
    ) {
        for (options in optionsGroup){
            Row {
                for (option in options) {
                    if (selectedAnswer != null) {
                        if (option.id == selectedAnswer) {
                            selected = option.id
                            selectedAnswer = null
                        }
                    }
                    TopicCard(
                        title = option.label,
                        modifier = Modifier
                            .height(height = LookDefault.Size.large)
                            .weight(0.5f),
                        colorTitle = if (option.id == selected) MaterialTheme.colorScheme.secondary
                        else MaterialTheme.colorScheme.onPrimary,
                        colors = CardDefaults.cardColors(
                            containerColor = if (option.id == selected) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.secondary
                        ),
                        border = BorderStroke(
                            LookDefault.Stroke.small,
                            MaterialTheme.colorScheme.onPrimary
                        ),
                        verticalArrangement = Arrangement.Center,
                        onClick = {
                            selected = option.id
                            viewModel.saveAnswer(question.id, option)
                        }
                    )
                }
            }
        }
    }
}