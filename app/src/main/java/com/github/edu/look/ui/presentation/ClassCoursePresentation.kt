package com.github.edu.look.ui.presentation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.github.edu.look.R
import com.github.edu.look.ui.component.TopicCard
import com.github.edu.look.ui.theme.LookDefault

@Composable
fun ClassCoursePresentation(
    navController: NavController,
    classroomId: Long?
    ) {

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.secondary)
    ){
        Spacer(modifier = Modifier.padding(vertical = LookDefault.Padding.large))
        TopicCard(
            title = stringResource(R.string.communication),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
            colorTitle = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .weight(1f)
                .padding(all = LookDefault.Padding.large),
            verticalArrangement = Arrangement.Center,
            onClick = {navController.navigate( "${RouterSet.ClassTopicPresentation.name}/" +
                    "$classroomId?type=${ClassType.COMMUNICATE.name}" )}
        )

        TopicCard(
            title = stringResource(R.string.classTitle),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
            colorTitle = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .weight(1f)
                .padding(all = LookDefault.Padding.large),
            verticalArrangement = Arrangement.Center,
                    onClick = {navController.navigate( "${RouterSet.ClassTopicPresentation.name}/" +
                            "$classroomId?type=${ClassType.CLASS_TEXT.name}" )}
        )

        TopicCard(
            title = stringResource(R.string.homework),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
            colorTitle = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .weight(1f)
                .padding(all = LookDefault.Padding.large),
            verticalArrangement = Arrangement.Center,
            onClick = {navController.navigate( "${RouterSet.ClassTopicPresentation.name}/" +
                    "$classroomId?type=${ClassType.HOMEWORK.name}" )}
        )
        Spacer(modifier = Modifier.padding(vertical = LookDefault.Padding.large))
    }
}

enum class ClassType {
    HOMEWORK,
    COMMUNICATE,
    CLASS_TEXT
}