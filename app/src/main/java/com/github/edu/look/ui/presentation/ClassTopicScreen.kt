package com.github.edu.look.ui.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.github.edu.look.R
import com.github.edu.look.ui.component.ScaleText
import com.github.edu.look.ui.theme.LookDefault
import com.github.edu.look.ui.viewmodel.classtopic.ClassTopicViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassTopicScreen(
    navController: NavHostController,
    classTopicViewModel: ClassTopicViewModel = viewModel()
) {

    val topics by classTopicViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { R.string.classTitle },
                navigationIcon = { IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(R.drawable.back_button),
                        contentDescription = stringResource(R.string.back)
                    )
                }}
                )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(R.drawable.settings),
                    contentDescription = stringResource(R.string.settings)
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(it)
        ) {
            items(items = topics) { item ->
                ClassTopicItem(topic = item.topic, date = item.date)
            }
        }
    }
}

@Composable
fun ClassTopicItem(
    topic: String,
    date: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card (
        modifier = modifier
            .padding(all = LookDefault.Padding.middle)
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier
            .semantics(mergeDescendants = true) {}
            .fillMaxWidth()
            .padding(all = LookDefault.Padding.middle)
        ) {
            ScaleText(text = topic, fontSize = 14.sp)
            ScaleText(text = stringResource(id = R.string.posted, date), fontSize = 14.sp)
        }

    }
}