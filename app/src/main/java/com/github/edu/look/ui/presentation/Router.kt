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
import androidx.compose.foundation.layout.padding
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
import com.github.edu.look.R
import com.github.edu.look.ui.component.ScaleText
import com.github.edu.look.ui.presentation.configuration.ConfigurationPresentation
import com.github.edu.look.ui.theme.LookDefault
import com.github.edu.look.ui.viewmodel.classtopic.CommunicationViewModel

enum class RouterSet(val title: String) {
    ClassTopicPresentation("Aulas"),
    MorePresentation("Mais"),
    ClassCoursePresentation("Disciplina"),
    LoginPresentation("Login"),
    LoadingPresentation("Loading"),
    CommunicationPresentation("Comunicados")
}

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val iconSelected: ImageVector = icon,
    val route: String,
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Router() {

    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.tertiary),
        topBar = {
            if(currentRoute != RouterSet.LoginPresentation.name
                && currentRoute != RouterSet.LoadingPresentation.name) {
                TopAppBar(
                    title = {
                        ScaleText(
                            text = stringResource(R.string.classTitle),
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = LookDefault.FontSize.small
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.back)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                )
            }
        },
        floatingActionButton = {
            if (currentRoute != RouterSet.LoginPresentation.name
                && currentRoute != RouterSet.LoadingPresentation.name
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
        bottomBar = {
            if (currentRoute != RouterSet.LoginPresentation.name
                && currentRoute != RouterSet.LoadingPresentation.name) {
                BottomNavigationBar(navController = navController)
            }
        }
    )
}

@Composable
fun BottomNavigationBar(
    navController: NavHostController
) {
    BottomNavigation(
        modifier = Modifier
            .defaultMinSize(minHeight = LookDefault.Padding.ultraLarge)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val bottomNavItems = listOf(
            BottomNavItem(
                label = "Disciplina",
                icon = Icons.Filled.Home,
                iconSelected = Icons.Outlined.Home,
                route = RouterSet.ClassCoursePresentation.name
            ),
            BottomNavItem(
                label = "Aulas",
                icon = Icons.Filled.Settings,
                iconSelected = Icons.Outlined.Settings,
                route = RouterSet.ClassTopicPresentation.name
            )
        )

        bottomNavItems.forEach { navItem ->
            BottomNavigationItem(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f, false),
                selected = currentRoute == navItem.route,
                onClick = {
                    if (currentRoute == navItem.route)
                        return@BottomNavigationItem
                    navController.navigate(route = navItem.route)
                },
                icon = navItem.icon,
                iconSelected = navItem.iconSelected,
                label = navItem.label,
            )
        }

    }
}


@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues,
) {
    val communicationViewModel  = CommunicationViewModel()
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
                ConfigurationPresentation()
            }
            composable(RouterSet.ClassTopicPresentation.name) {
                ClassTopicPresentation()
            }
            composable(RouterSet.ClassCoursePresentation.name) {
                ClassCoursePresentation()
            }
            composable(RouterSet.CommunicationPresentation.name) {
                CommunicationPresentation(communicationViewModel,navController, RouterSet.CommunicationPresentation)
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