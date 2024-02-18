package com.github.edu.look.ui.viewmodel.homework

import androidx.lifecycle.ViewModel
import com.github.edu.look.data.homework.Option
import com.github.edu.look.data.homework.Question
import com.github.edu.look.repository.HomeworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeworkAnswerViewModel @Inject constructor(
    private val homeworkRepository: HomeworkRepository
): ViewModel() {
    val answers: List<Pair<Question, Option>>
        get() = homeworkRepository.homeworkAnswers
    val classroomId: Long
        get() = homeworkRepository.classroomId ?: -1
}