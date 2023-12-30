package com.github.edu.look.ui.viewmodel.classTexts

import androidx.lifecycle.ViewModel
import com.github.edu.look.data.classTexts.ClassTexts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ClassTextsViewModel @Inject constructor(): ViewModel() {
        private var _uiState =  MutableStateFlow(mutableListOf<ClassTexts>())
        val uiState get() = _uiState.asStateFlow()

        init {
            _uiState = MutableStateFlow(mutableListOf(
                ClassTexts(id = 1,  publicationDateText = "20/03/2023", textContent =
                "As figuras de linguagem são recursos linguísticos a que os autores recorrem para tornar a" +
                        "linguagem mais rica e expressiva. Esses recursos revelam a sensibilidade de quem os" +
                        " utiliza, traduzindo particularidades estilísticas do emissor da linguagem. As figuras " +
                        "de linguagem exprimem também o pensamento de modo original e criativo, exploram o sentido" +
                        " não literal das palavras, realçam sonoridade de vocábulos e frases e até mesmo, organizam " +
                        "orações, afastando-a, de algum modo, de uma estrutura gramatical padrão, a fim de dar destaque" +
                        "a algum de seus elementos. As figuras de linguagem costumam ser classificadas em figuras de som," +
                        " figuras de construção e figuras de palavras ou semânticas.\n" +
                        "\n" +
                        "Antes de apresentarmos quais são as figuras de linguagem mais comuns, estudaremos, de modo sintetizado, os conceitos de denotação e conotação.\n" +
                        "\n" +
                        "Denotação\n" +
                        "\n" +
                        "Ocorre denotação quando a palavra é empregada em sua significação usual, literal, referindo-se a uma realidade concreta ou imaginária.\n" +
                        "Já é a quinta vez que perco as chaves do meu armário\n" +
                        "Aquela sobremesa estava muito azeda, não gostei.\n" +
                        "Conotação\n" +
                        "Ocorre a conotação quando a palavra é empregada em sentido figurado, associativo, possibilitando várias interpretações. Ou seja, o sentido conotativo tem a propriedade de atribuir às palavras significados diferentes de seu sentido original.\n" +
                        "A chave da questão é você ser feliz independente do momento\n" +
                        "Margarida é uma mulher azeda, está sempre de péssimo humor.\n" +
                        "Podemos perceber que as palavras chave e azeda ganham novos sentidos além dos quais encontramos nos dicionários. O sentido das palavras está de acordo com a ideia que o emissor quis transmitir. Sendo assim, a conotação é um recurso que consiste em atribuir novos significados ao sentido denotativo da palavra.", textTitle = "Figuras de Linguagem:\n" +
                        "O que são e como aplicar?"),

            ))
        }

}