package com.github.dannrocha.look.ui.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.github.dannrocha.look.ui.viewmodel.configuration.SharedConfigurationViewModel
import com.github.dannrocha.look.ui.viewmodel.overview.OverviewViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OverviewPresentation(
    navController: NavHostController,
    overviewViewModel: OverviewViewModel = viewModel(),
    sharedConfigurationViewModel: SharedConfigurationViewModel
) {

    val classes by overviewViewModel.uiState.collectAsState()
    val fontScale by sharedConfigurationViewModel.fontScale.collectAsState(initial = 1.sp)

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(items = classes) { item ->
            ClassItem(className =  item.name, teacherName = item.teacherName, fontScale = fontScale) {}
        }
    }
}

@Composable
fun ClassItem(
    className: String,
    teacherName: String,
    modifier: Modifier = Modifier,
    fontScale: TextUnit = 1.sp,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .padding(all = 8.dp)
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier
            .semantics(mergeDescendants = true) {}
            .fillMaxWidth()
            .padding(all = 8.dp)
        ) {
            Text(text = className, fontSize = 30.times(fontScale.value).sp)
            Text(text = teacherName, fontSize = 25.times(fontScale.value).sp)
        }
    }
}