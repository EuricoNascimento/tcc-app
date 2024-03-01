package com.github.edu.look.ui.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.github.edu.look.ui.component.TopicCard
import com.github.edu.look.ui.theme.LookDefault
import com.github.edu.look.ui.viewmodel.CourseViewModel

@Composable
fun CoursePresentation(
    navController: NavController,
    courseViewModel: CourseViewModel = hiltViewModel()
) {
    val classrooms by courseViewModel.coursesState.collectAsState()
    if (classrooms.isEmpty()) {
        courseViewModel.getCourses()
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.secondary)
    )  {
        items(items = classrooms) { item ->
            TopicCard(
                title = item.name ?: "",
                subTitle = item.teachers?.firstOrNull()?.name ?: "",
                border = BorderStroke(LookDefault.Stroke.small, MaterialTheme.colorScheme.onPrimary),
                onClick = {
                    navController.navigate("${RouterSet.ClassCoursePresentation.name}/${item.id}")
                }
            )
        }
    }
}