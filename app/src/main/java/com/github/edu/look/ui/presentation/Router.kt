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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.lifecycle.viewmodel.compose.viewModel
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

import com.github.edu.look.ui.viewmodel.homework.HomeworkViewModel


enum class RouterSet(val title: String) {
    ClassTopicPresentation("Aulas"),
    MorePresentation("Mais"),
    ClassCoursePresentation("Disciplina"),
    LoginPresentation("Login"),
    LoadingPresentation("Loading"),
    CommunicationPresentation("Comunicados"),
    ClassTextsPresentation("Textos"),
    HomeworkQuestionPresentation("Atividade"),
    HomeworkAnswersPresentation("Respostas"),
    CoursePresentation("Turmas")
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Router() {

    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var currentRoute = navBackStackEntry?.destination?.route ?: ""
    val ignoredScreen = listOf(RouterSet.LoginPresentation.name, RouterSet.LoadingPresentation.name)
    val alteredScreen = listOf(
        RouterSet.HomeworkAnswersPresentation.name,
        RouterSet.HomeworkQuestionPresentation.name
    )

    if (currentRoute.contains('/')) {
        val listRouter = currentRoute.split('/')
        currentRoute = listRouter[0]
    }

    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.tertiary),
        topBar = {
            if(!ignoredScreen.contains(currentRoute)) {
                var backgroundColor = MaterialTheme.colorScheme.primary
                var letterColor = MaterialTheme.colorScheme.onPrimary
                if (alteredScreen.contains(currentRoute)) {
                    backgroundColor = MaterialTheme.colorScheme.onPrimary
                    letterColor = MaterialTheme.colorScheme.secondary
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(LookDefault.Size.normal)
                        .clip(
                            RoundedCornerShape(
                                bottomEnd = LookDefault.Padding.extraLarge,
                                bottomStart = LookDefault.Padding.extraLarge
                            )
                        )
                        .background(backgroundColor),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement =
                    if (currentRoute != RouterSet.HomeworkAnswersPresentation.name)
                        Arrangement.SpaceBetween
                    else
                        Arrangement.Center
                ) {
                    if (currentRoute != RouterSet.HomeworkAnswersPresentation.name) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(horizontal = LookDefault.Padding.large)
                                .clickable { navController.popBackStack() }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.back),
                                tint = MaterialTheme.colorScheme.tertiary
                            )
                            ScaleText(
                                text = stringResource(R.string.back),
                                color = letterColor,
                                fontWeight = FontWeight.Bold,
                                fontSize = LookDefault.FontSize.medium
                            )
                        }
                    }
                    val route = RouterSet.values().firstOrNull {it.name == currentRoute}
                    ScaleText(
                        text = route?.title ?: "",
                        color = letterColor,
                        fontWeight = FontWeight.Bold,
                        fontSize = LookDefault.FontSize.medium,
                        modifier = Modifier.padding(horizontal = LookDefault.Padding.large)
                    )
                }
            }
        },
        floatingActionButton = {
            if (!ignoredScreen.contains(currentRoute) && !alteredScreen.contains(currentRoute)
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
        },
        containerColor = MaterialTheme.colorScheme.secondary
    )
}

@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues,
) {
    val viewModel: HomeworkViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = RouterSet.LoadingPresentation.name,
        modifier = Modifier
            .padding(paddingValues = padding)
            .background(color = MaterialTheme.colorScheme.tertiary),
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
                    classroomId = it.arguments?.getLong("classroomId", 0L)
                )
            }
            composable("${RouterSet.ClassTopicPresentation.name}/{classroomId}?type={type}") {
                ClassTopicPresentation(
                    navController = navController,
                    classroomId = it.arguments?.getLong("classroomId", 0L),
                    type = it.arguments?.getString("type")
                )
            }
            composable(route = "${RouterSet.HomeworkQuestionPresentation.name}/" +
                    "{classroomId}/{topicId}?questionId={questionId}?answer={answer}",
                arguments = listOf(
                    navArgument("questionId") { defaultValue = 0L },
                    navArgument("answer") { defaultValue = "" }
                )) {
                HomeworkQuestionPresentation(
                    navController = navController,
                    classroomId = it.arguments?.getLong("classroomId", 0L),
                    homeworkId = it.arguments?.getLong("topicId", 0L),
                    questionId = it.arguments?.getLong("questionId"),
                    answer = it.arguments?.getString("answer"),
                    viewModel = viewModel
                )
            }

            composable(RouterSet.HomeworkAnswersPresentation.name) {
                HomeworkAnswersPresentation(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }

            )
}

data class MenuItem(
    val icon: ImageVector,
    val index: Int,
    val label: String = "",
    val selected: Boolean = false,
    val perform: (NavController) -> Unit = {}
)

@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier,
    content: @Composable (RowScope.() -> Unit),
) {
    BottomAppBar(
        contentColor = MaterialTheme.colorScheme.onPrimary,
        containerColor = MaterialTheme.colorScheme.primary,
        modifier = modifier,
        content = {
            Row(
                content = content,
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            )
        },
    )
}
@Composable
fun BottomNavigationItem(
    icon: ImageVector,
    label: String,
    iconSelected: ImageVector? = null,
    selected: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {


    val colorsSchemaSelected =
        if (selected)
            MaterialTheme.colorScheme.secondary
        else
            MaterialTheme.colorScheme.primary

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .semantics(mergeDescendants = true) {}
            .background(colorsSchemaSelected)
    ) {
        Icon(
            imageVector = if (selected && iconSelected != null) iconSelected else icon,
            contentDescription = null,
            modifier = Modifier
                .clip(MaterialTheme.shapes.extraLarge)
                .background(color = colorsSchemaSelected)

        )
        if (label.isNotBlank()) {
            ScaleText(
                text = label,
                fontSize = LookDefault.FontSize.medium,
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                maxLines = 1
            )
        }
    }
}



