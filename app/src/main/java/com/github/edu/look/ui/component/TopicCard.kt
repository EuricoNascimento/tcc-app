package com.github.edu.look.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.sp
import com.github.edu.look.R
import com.github.edu.look.ui.theme.LookDefault

@Composable
fun TopicCard(
    title: String,
    subTitle: String? = null,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    border: BorderStroke = BorderStroke(LookDefault.Stroke.none, MaterialTheme.colorScheme.onPrimary),
    colors: CardColors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary)
) {
    Card (
        modifier = modifier
            .padding(all = LookDefault.Padding.middle)
            .clickable { onClick() },
        border = border,
        colors = colors
    ) {
        Column(modifier = Modifier
            .semantics(mergeDescendants = true) {}
            .fillMaxWidth()
            .padding(all = LookDefault.Padding.middle)
        ) {
            ScaleText(
                text = title,
                fontSize = 22.sp,
                color = Color.Green,
                modifier = Modifier
                    .padding(LookDefault.Padding.middle)
                    .align(alignment = Alignment.CenterHorizontally)
            )
            subTitle?.let {
                ScaleText(
                    text = it,
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(LookDefault.Padding.middle)
                        .align(alignment = Alignment.CenterHorizontally)
                )
            }
        }

    }
}