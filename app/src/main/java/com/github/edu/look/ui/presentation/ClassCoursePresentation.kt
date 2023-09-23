package com.github.edu.look.ui.presentation


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.github.edu.look.R
import com.github.edu.look.ui.component.TopicCard
import com.github.edu.look.ui.theme.LookDefault

@Composable
fun ClassCoursePresentation() {

    Column (modifier = Modifier.fillMaxSize()){
        Spacer(modifier = Modifier.padding(vertical = LookDefault.Padding.large))
        TopicCard(
            title = stringResource(R.string.comunication),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
            colorTitle = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .weight(1f)
                .padding(all = LookDefault.Padding.large)
        )

        TopicCard(
            title = stringResource(R.string.classTitle),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
            colorTitle = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .weight(1f)
                .padding(all = LookDefault.Padding.large)
        )

        TopicCard(
            title = stringResource(R.string.homework),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
            colorTitle = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .weight(1f)
                .padding(all = LookDefault.Padding.large)
        )
        Spacer(modifier = Modifier.padding(vertical = LookDefault.Padding.large))
    }
}
