package com.github.edu.look.ui.viewmodel.homework

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.github.edu.look.data.homework.Homework
import com.github.edu.look.data.homework.Option
import com.github.edu.look.data.homework.Question
import com.github.edu.look.services.HomeworkService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeworkQuestionViewModel @Inject constructor(
    private val homeworkService: HomeworkService
): ViewModel() {
    private var _uiState = MutableStateFlow(Homework())
    val uiState
        get() = _uiState.asStateFlow()
    private val answersState: MutableStateFlow<MutableMap<Long, Option>> = MutableStateFlow( mutableMapOf() )
    private val currentQuestion: MutableState<Question> = mutableStateOf(Question())
    var questionNumber = 1

    fun startRequisition(classroomId: Long?, topicId: Long?) {
        val homework = homeworkService.startRequisition(classroomId, topicId) ?: return
        _uiState = MutableStateFlow(homework)
    }

    fun getAnswer(questionId: Long): Option? {
        return answersState.value.get(questionId)
    }

    fun updateQuestion(id: Long?) {
        currentQuestion.value = getQuestion(id)
    }

    fun getFirstQuestion(id: Long?): MutableState<Question> {
        currentQuestion.value = getQuestion(id)
        return currentQuestion
    }

    private fun getQuestion(id: Long?): Question {
        return if (id != null && id >= 0) {
            uiState.value.question.first { id == it.id }
        } else {
            uiState.value.question.first { !answersState.value.keys.contains(it.id) }
        }
    }

    fun allQuestionAnswer(): Boolean {
        return answersState.value.keys.containsAll(_uiState.value.question.map { it.id})
    }

    fun wasAnsweredQuestion(id: Long?): Boolean {
        return if (id == null) return false else answersState.value.keys.contains(id)
    }

    fun saveAnswer(idQuestion: Long, answer: Option) {
        answersState.value[idQuestion] = answer
    }

    fun updateAnswer(questionId: Long) {
        val updateAnswer = Pair(getQuestion(answersState.value.keys.first()), answersState.value.values.first())
        val allAnswer = homeworkService.homeworkAnswers.map {
            if (it.first.id == questionId) updateAnswer else it
        }
        homeworkService.homeworkAnswers = allAnswer
    }

    fun saveAllAnswer() {
        homeworkService.homeworkAnswers = answersState.value.map { Pair(getQuestion(it.key), it.value) }
    }
}

