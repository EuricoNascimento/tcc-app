package com.github.edu.look.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.github.edu.look.R
import com.github.edu.look.ui.component.ScaleText
import com.github.edu.look.ui.presentation.configuration.ConfigurationPresentation
import com.github.edu.look.ui.theme.LookDefault

enum class RouterSet(val title: String) {
    ClassTopicPresentation("Aulas"),
    MorePresentation("Mais"),
    ClassCoursePresentation("Disciplina"),
    LoginPresentation("Login"),
    LoadingPresentation("Loading"),
    CommunicationPresentation("Comunicados"),
    HomeworkQuestionPresentation("Atividade"),
    HomeworkAnswersPresentation("Respostas"),
    CoursePresentation("Turmas")
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Router() {

    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val ignoredScreen = listOf(RouterSet.LoginPresentation.name, RouterSet.LoadingPresentation.name)

    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.tertiary),
        topBar = {
            if(!ignoredScreen.contains(currentRoute)) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(LookDefault.Size.normal)
                        .background(MaterialTheme.colorScheme.primary),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { navController.popBackStack() }, modifier = Modifier.size(LookDefault.Size.small)) {
                            Icon(imageVector = Icons.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.back))
                        }
                        ScaleText(
                            text = stringResource(R.string.back),
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = LookDefault.FontSize.medium
                        )
                    }
                    val route = RouterSet.values().firstOrNull {it.name == currentRoute}
                    ScaleText(
                        text = route?.title ?: "",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = LookDefault.FontSize.medium,
                        modifier = Modifier.padding(end = LookDefault.Padding.middle)
                    )
                }
            }
        },
        floatingActionButton = {
            if (!ignoredScreen.contains(currentRoute)
                && currentRoute != RouterSet.MorePresentation.name) {
                FloatingActionButton(
                    onClick = { navController.navigate(RouterSet.MorePresentation.name) },
                    shape = MaterialTheme.shapes.large,
                    containerColor = MaterialTheme.colorScheme.onPrimary
                ) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = stringResource(R.string.settings),
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        },
        content = { padding ->
            NavHostContainer(navController = navController, padding = padding)
        }
    )
}

@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues,
) {
    NavHost(
        navController = navController,
        startDestination = RouterSet.LoadingPresentation.name,
        modifier = Modifier.padding(paddingValues = padding),
        builder = {
            composable(RouterSet.LoadingPresentation.name) {
                LoadingPresentation(navController, RouterSet.LoginPresentation)
            }
            composable(RouterSet.LoginPresentation.name) {
                LoginPresentation(navController)
            }
            composable(RouterSet.MorePresentation.name) {
                ConfigurationPresentation(navController)
            }
            composable(RouterSet.CoursePresentation.name) {
                CoursePresentation(navController = navController)
            }
            composable("${RouterSet.ClassCoursePresentation.name}/{classroomId}") {
                ClassCoursePresentation(
                    navController = navController,
                    classroomId = it.arguments?.getLong("classroomId", 0L))
            }
            composable("${RouterSet.ClassTopicPresentation.name}/{classroomId}?type={type}") {
                ClassTopicPresentation(
                    navController = navController,
                    classroomId = it.arguments?.getLong("classroomId", 0L),
                    type = it.arguments?.getString("type")
                )
            }
            composable(route = "${RouterSet.HomeworkQuestionPresentation.name}/" +
                    "{classroomId}/{topicId}?questionId={questionId}",
                arguments = listOf(navArgument("questionId") { defaultValue = 0L })) {
                HomeworkQuestionPresentation(
                    navController = navController,
                    classroomId = it.arguments?.getLong("classroomId", 0L),
                    homeworkId = it.arguments?.getLong("topicId", 0L),
                    questionId = it.arguments?.getLong("questionId")
                )
            }
            composable(RouterSet.HomeworkAnswersPresentation.name) {
                HomeworkAnswersPresentation(navController)
            }
            composable(RouterSet.CommunicationPresentation.name) {
                CommunicationPresentation(navController = navController)
            }
        }
    )
}