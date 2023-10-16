package com.github.edu.look.ui.presentation


import android.graphics.fonts.FontStyle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

import com.github.edu.look.data.classTexts.ClassTexts
import com.github.edu.look.ui.component.ScaleText

import com.github.edu.look.ui.theme.GreenLight
import com.github.edu.look.ui.theme.LookDefault
import com.github.edu.look.ui.viewmodel.classTexts.ClassTextsViewModel



@Composable
fun ClassTextsPresentation(
    classTextsViewModel: ClassTextsViewModel = viewModel(),
    navController: NavHostController,
    routerSet: RouterSet

) {
    val classTexts by classTextsViewModel.uiState.collectAsState()
    ClassTextsItems(classTexts)
    navController.navigate(routerSet.name)


}

@Composable
fun ClassTextsItems(classTexts: List<ClassTexts>, modifier: Modifier = Modifier) {
    Surface(

        modifier = modifier
            .fillMaxSize()

    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            items(items = classTexts) { item ->

                    ScaleText(
                        text = item.textTitle,
                        fontSize = LookDefault.FontSize.medium,
                        color = GreenLight,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(top =  LookDefault.Padding.large,bottom = LookDefault.Padding.middle)
                    )


                ScaleText(
                        text = "Postado em: " + item.publicationDateText,
                        fontSize = LookDefault.FontSize.small,
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = LookDefault.Padding.extraLarge)

                    )

                ScaleText(
                        text = item.textContent,
                        fontSize = LookDefault.FontSize.medium,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier.padding(all = LookDefault.Padding.large)
                    )

                }
            }
        }
    }







