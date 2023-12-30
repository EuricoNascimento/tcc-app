package com.github.edu.look.ui.viewmodel.homework

import androidx.lifecycle.ViewModel
import com.github.edu.look.data.homework.Option
import com.github.edu.look.data.homework.Question
import com.github.edu.look.services.HomeworkService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeworkAnswerViewModel @Inject constructor(
    private val homeworkService: HomeworkService
): ViewModel() {
    val answers: List<Pair<Question, Option>>
        get() = homeworkService.homeworkAnswers
    val classroomId: Long
        get() = homeworkService.classroomId ?: -1
}