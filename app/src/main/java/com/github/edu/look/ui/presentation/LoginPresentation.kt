package com.github.edu.look.ui.presentation

import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
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
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.github.edu.look.R
import com.github.edu.look.ui.component.ScaleText
import com.github.edu.look.ui.theme.LookDefault
import com.github.edu.look.ui.viewmodel.LoginViewModel
import com.github.edu.look.utils.GoogleApiContract
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import okhttp3.Route


@Composable
fun LoginPresentation(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel()
)  {
    var isLogged by remember { mutableStateOf(false) }
    val authResultLauncher =
        rememberLauncherForActivityResult(contract = GoogleApiContract()) { task ->
            try {
                val gsa = task?.getResult(ApiException::class.java)

                if (gsa != null) {
                    isLogged = loginViewModel.signIn(gsa.idToken)
                }
            } catch (e: Exception) {
               Log.e("ERROR", e.toString())
            }
            if (isLogged) {
                navController.navigate(RouterSet.CoursePresentation.name) {
                    popUpTo(RouterSet.LoginPresentation.name) {
                        inclusive = true
                    }
                }
            }
        }
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
                .size(LookDefault.Size.extraLarge)
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
        GoogleButton(navController, authResultLauncher)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoogleButton(
    navController: NavController,
    authResultLauncher: ManagedActivityResultLauncher<Int, Task<GoogleSignInAccount>?>
) {

    val signInRequestCode = 1
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = LookDefault.Padding.extraLarge),
        color = MaterialTheme.colorScheme.onPrimary,
        shape = MaterialTheme.shapes.large,
        onClick = {
            authResultLauncher.launch(signInRequestCode)
        }
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