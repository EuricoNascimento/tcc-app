package com.github.edu.look.ui.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.github.edu.look.R
import com.github.edu.look.data.classtopic.ClassTopic
import com.github.edu.look.ui.component.TopicCard
import com.github.edu.look.ui.theme.LookDefault
import com.github.edu.look.ui.viewmodel.CourseViewModel
import com.github.edu.look.ui.viewmodel.classtopic.ClassTopicViewModel

@Composable
fun CoursePresentation(
    navController: NavController,
    courseViewModel: CourseViewModel = viewModel(),
) {
    val classrooms by courseViewModel.uiState.collectAsState()
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn {
            items(items = classrooms) { item ->
                TopicCard(
                    title = item.name,
                    subTitle = item.teacherName,
                    border = BorderStroke(LookDefault.Stroke.small, MaterialTheme.colorScheme.onPrimary),
                    onClick = {
                        navController.navigate("${RouterSet.ClassCoursePresentation.name}/${item.id}")
                    }
                )
            }
        }
    }
}