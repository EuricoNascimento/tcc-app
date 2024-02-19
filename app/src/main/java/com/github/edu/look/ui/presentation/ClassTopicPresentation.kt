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
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.github.edu.look.R
import com.github.edu.look.data.enums.ClassType
import com.github.edu.look.ui.component.TopicCard
import com.github.edu.look.ui.theme.LookDefault
import com.github.edu.look.ui.viewmodel.classtopic.ClassTopicViewModel

@Composable
fun ClassTopicPresentation(
    navController: NavController,
    classroomId: String?,
    type: String?,
    viewModel: ClassTopicViewModel = hiltViewModel()
) {
    if (type.isNullOrEmpty() || classroomId == null) {
        navController.popBackStack()
        return
    }

    if (viewModel.classType == null || viewModel.classType?.name != type) {
        viewModel.classType = ClassType.getClassType(type)
        viewModel.getClassTopic(classroomId)
    }

    val classMaterial by viewModel.materialTopicsState.collectAsState()
    val announcements by viewModel.uiState.collectAsState()

    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.secondary)
    ) {
        when(viewModel.classType) {
            ClassType.CLASS_MATERIAL -> {
                items(items = classMaterial) { item ->
                    TopicCard(
                        title = item.title,
                        subTitle = stringResource(id = R.string.posted, item.createdAt),
                        border = BorderStroke(
                            LookDefault.Stroke.small,
                            MaterialTheme.colorScheme.onPrimary
                        ),
                        onClick = {
//                            navController.navigate(
//                                RouterSet.ClassMaterialsPresentation.name
//                                        + "/$classroomId/$type/${item.id}"
//                            )
                        }
                    )
                }
            }
            ClassType.COMMUNICATE -> {
                items(items = announcements) { item ->
                    TopicCard(
                        title = item.topic,
                        subTitle = stringResource(id = R.string.posted, item.date),
                        border = BorderStroke(
                            LookDefault.Stroke.small,
                            MaterialTheme.colorScheme.onPrimary
                        ),
                        onClick = {
                            navController.navigate(
                                RouterSet.ClassMaterialsPresentation.name
                                        + "/$classroomId/$type/${item.id}"
                            )
                        }
                    )
                }
            }
            else -> {}
        }
    }
}