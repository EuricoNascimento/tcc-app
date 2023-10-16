package com.github.edu.look.ui.viewmodel.homework

import androidx.lifecycle.ViewModel
import com.github.edu.look.data.homework.Homework
import com.github.edu.look.data.homework.Question
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.min

class HomeworkViewModel : ViewModel() {
    private var _uiState = MutableStateFlow(Homework())
    val uiState get() = _uiState.asStateFlow()
    private val answers: MutableMap<Long, String> = mutableMapOf()
    private var question = listOf<Question>()
    var questionNumber = 1
    var classroomId: Long? = null
    var homeworkId: Long? = null

    init {
        question = listOf(
            Question(
                id = 0,
                question = "Quais foram os elementos quimicos que abasteceram as bombas de Hiroshima e Nagasaki",
                option = listOf("Plutonio e Uranio", "Plutonio e Neuronio", "Uranus e Netuno", "Nenhuma das alteranativas")
            ),
            Question(
                id = 1,
                question = "2 * (10 - 8 + 7)",
                option = listOf("2", "4", "6", "10")
            )
        )
        _uiState = MutableStateFlow(
            Homework(id = 0, homeworkName = "Tabela periodica parte 1", date = "20/03/2023", question = question)
        )
    }

    fun getQuestionWithAnswer():  List<Pair<Question, String>>{
        return answers.map { Pair(getQuestion(it.key), it.value) }
    }

    fun getNextQuestion(currentQuestion: Question): Long {
        val listQuestion = uiState.value.question
        val index = listQuestion.indexOf(currentQuestion)
        val currentIndex = min(index + 1, listQuestion.size)

        return listQuestion[currentIndex].id
    }

    fun getQuestion(id: Long): Question {
        return uiState.value.question.firstOrNull { id == it.id } ?: uiState.value.question.first()
    }

    fun isLastQuestion(question: Question): Boolean {
        return uiState.value.question.last().id == question.id
    }

    fun wasAnsweredQuestion(id: Long): Boolean {
        return answers.keys.contains(id)
    }

    fun saveAnswer(idQuestion: Long, answer: String) {
        answers[idQuestion] = answer
    }
}