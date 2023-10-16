package com.github.edu.look.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import com.github.edu.look.ui.theme.LookDefault

@Composable
fun TopicCard(
    title: String,
    subTitle: String? = null,
    modifier: Modifier = Modifier,
    contentModifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    onClick: () -> Unit = {},
    border: BorderStroke = BorderStroke(LookDefault.Stroke.none, MaterialTheme.colorScheme.onPrimary),
    colors: CardColors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondary),
    colorSubTitle: Color = MaterialTheme.colorScheme.onPrimary,
    colorTitle: Color = MaterialTheme.colorScheme.primary
) {
    Card (
        modifier = modifier
            .padding(all = LookDefault.Padding.middle)
            .clickable { onClick() },
        border = border,
        colors = colors
    ) {
        Column(modifier = contentModifier
            .semantics(mergeDescendants = true) {}
            .fillMaxSize()
            .padding(all = LookDefault.Padding.middle),
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment
        ) {
            ScaleText(
                text = title,
                fontSize = LookDefault.FontSize.medium,
                color = colorTitle,
                modifier = Modifier
                    .padding(LookDefault.Padding.small)
                    .align(alignment = Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )
            subTitle?.let {
                ScaleText(
                    text = it,
                    fontSize = LookDefault.FontSize.small,
                    color = colorSubTitle,
                    modifier = Modifier
                        .padding(LookDefault.Padding.small)
                        .align(alignment = Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center
                )
            }
        }

    }
}