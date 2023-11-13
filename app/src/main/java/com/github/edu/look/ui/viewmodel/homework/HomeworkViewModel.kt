package com.github.edu.look.ui.viewmodel.homework

import androidx.lifecycle.ViewModel
import com.github.edu.look.data.homework.Homework
import com.github.edu.look.data.homework.Option
import com.github.edu.look.data.homework.Question
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeworkViewModel : ViewModel() {
    private var _uiState = MutableStateFlow(Homework())
    val uiState get() = _uiState.asStateFlow()
    private val answers: MutableMap<Long, Option> = mutableMapOf()
    private var question = listOf<Question>()
    var questionNumber = 1
    var classroomId: Long? = null
    var homeworkId: Long? = null

    init {
        question = listOf(
            Question(
                id = 0,
                question = "(Enem/2013) O contribuinte que vende mais de R\$ 20 mil de ações em " +
                        "Bolsa de Valores em um mês deverá pagar Imposto de Renda. O pagamento para " +
                        "a Receita Federal consistirá em 15% do lucro obtido com a venda das ações." +
                        "Um contribuinte que vende por R\$ 34 mil um lote de ações que custou R\$ 26 " +
                        "mil terá de pagar de Imposto de Renda à Receita Federal o valor de\n",
                option = listOf(
                    Option(id = 0, "R\$ 900,00"),
                    Option(id = 1, "R\$ 1 200,00"),
                    Option(id = 2, "R\$ 2 100,00"),
                    Option(id = 3, "R\$ 3 900,00")
                )
            ),
            Question(
                id = 1,
                question = "2 * (10 - 8 + 7)",
                option = listOf(
                    Option(id = 0, "2"),
                    Option(id = 1, "4"),
                    Option(id = 2, "8"),
                    Option(id = 3, "10")
                )
            )
        )
        _uiState = MutableStateFlow(
            Homework(id = 0, homeworkName = "Tabela periodica parte 1", date = "20/03/2023", question = question)
        )
    }

    fun startRequesition(classroomId: Long?, topicId: Long?) {
        if (topicId != null && classroomId != null) {
            this.classroomId = classroomId
            this.homeworkId = topicId
        }
    }

    fun getQuestionWithAnswer():  List<Pair<Question, Option>>{
        return answers.map { Pair(getQuestion(it.key), it.value) }
    }

    fun getNextQuestionId(): Long {
        val listQuestion = uiState.value.question.toMutableList()
        val currentListQuestion = listQuestion.filter { !answers.keys.contains(it.id) }

        return currentListQuestion.first().id
    }

    fun getAnswer(questionId: Long): Option? {
        return answers.get(questionId)
    }

    fun getQuestion(id: Long?): Question {
        val teste = if (id != null) {
            uiState.value.question.first { id == it.id }
        } else {
            uiState.value.question.first { !answers.keys.contains(it.id) }
        }

        return teste
    }

    fun allQuestionAnswer(): Boolean {
        return answers.keys.containsAll(_uiState.value.question.map { it.id})
    }

    fun wasAnsweredQuestion(id: Long?): Boolean {
        return if (id == null) return false else answers.keys.contains(id)
    }

    fun saveAnswer(idQuestion: Long, answer: Option) {
        answers[idQuestion] = answer
    }
}