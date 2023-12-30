package com.github.edu.look.services

import com.github.edu.look.data.homework.Homework
import com.github.edu.look.data.homework.Option
import com.github.edu.look.data.homework.Question

class HomeworkService {
    private var cacheHomework: Homework? = null
    private var questions: MutableList<Question> = mutableListOf()
    var classroomId: Long? = null
    private var homeworkId: Long? = null
    var homeworkAnswers: List<Pair<Question, Option>> = listOf()

    init {
        questions.addAll(
            listOf(
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
                        Option(id = 5, "2"),
                        Option(id = 6, "4"),
                        Option(id = 7, "8"),
                        Option(id = 8, "10")
                    )
                )
            )
        )
        cacheHomework = Homework(
            id = 0,
            homeworkName = "Tabela periodica parte 1",
            date = "20/03/2023",
            question = questions
        )
    }

    fun startRequisition(classroomId: Long?, homeworkId: Long?): Homework? {
        if (homeworkId != null && classroomId != null) {
            this.classroomId = classroomId
            this.homeworkId = homeworkId
            return cacheHomework
        }
        return null
    }
}