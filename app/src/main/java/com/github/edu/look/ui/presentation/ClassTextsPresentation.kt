package com.github.edu.look.ui.presentation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.edu.look.data.classTexts.ClassTexts
import com.github.edu.look.ui.component.ScaleText
import com.github.edu.look.ui.theme.LookDefault
import com.github.edu.look.ui.viewmodel.classTexts.ClassTextsViewModel


@Composable
fun ClassTextsPresentation(
    navController: NavHostController,
    classroomId: Long?,
    type: String?,
    communicationId: Long?,
    classTextsViewModel: ClassTextsViewModel = hiltViewModel()
) {
    val classTexts by classTextsViewModel.uiState.collectAsState()
    ClassTextsItems(classTexts)
}

@Composable
fun ClassTextsItems(classTexts: List<ClassTexts>, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary)
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondary)
        ) {
            items(items = classTexts) { item ->
                ScaleText(
                    text = item.textTitle,
                    fontSize = LookDefault.FontSize.medium,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(top =  LookDefault.Padding.large,bottom = LookDefault.Padding.middle)
                )
                ScaleText(
                    text = "Postado em: " + item.publicationDateText,
                    fontSize = LookDefault.FontSize.small,
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = LookDefault.Padding.extraLarge)
                )
                ScaleText(
                    text = item.textContent,
                    fontSize = LookDefault.FontSize.medium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(all = LookDefault.Padding.large)
                )
            }
        }
    }
}