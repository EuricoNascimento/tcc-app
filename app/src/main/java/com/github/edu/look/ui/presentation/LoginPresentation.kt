package com.github.edu.look.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.github.edu.look.R
import com.github.edu.look.ui.component.ScaleText
import com.github.edu.look.ui.theme.LookDefault

@Composable
fun LoginPresentation(
    navController: NavController
)  {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(vertical = LookDefault.Padding.large))
        Icon(
            painter = painterResource(R.drawable.logo_look_with_name),
            contentDescription = stringResource(R.string.login_logo),
            modifier = Modifier
                .size(LookDefault.Size.big)
                .padding(vertical = LookDefault.Padding.extraLarge),
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.padding(vertical = LookDefault.Padding.extraLarge))
        ScaleText(
            text = stringResource(R.string.welcome),
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = LookDefault.Padding.large),
            fontSize = LookDefault.FontSize.medium
        )
        ScaleText(
            text = stringResource(R.string.begin_to_study),
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = LookDefault.Padding.large),
            fontSize = LookDefault.FontSize.small
        )
        GoogleButton(navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoogleButton(
    navController: NavController
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = LookDefault.Padding.extraLarge),
        color = MaterialTheme.colorScheme.onPrimary,
        shape = MaterialTheme.shapes.large,
        onClick = { navController.navigate(RouterSet.CoursePresentation.name) }
    ) {
        Row (
            modifier = Modifier.padding(all = LookDefault.Padding.large),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.logo_google),
                contentDescription = stringResource(R.string.login_with_google),
                modifier = Modifier
                    .size(LookDefault.Size.small)
                    .padding(end = LookDefault.Padding.large),
                tint = Color.Unspecified
            )
            ScaleText(
                text = stringResource(R.string.login_with_google),
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = LookDefault.FontSize.medium
            )
        }
    }
}