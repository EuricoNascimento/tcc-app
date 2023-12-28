package com.github.edu.look.ui.presentation


import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.github.edu.look.R
import com.github.edu.look.ui.theme.LookDefault
import kotlinx.coroutines.delay

@Composable
fun LoadingPresentation(
    navController: NavHostController,
    routerSet: RouterSet
) {
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.3f,
            animationSpec = tween(
                durationMillis = LookDefault.TimeDuration.veryShort,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(LookDefault.TimeDuration.normal.toLong())

        navController.navigate(routerSet.name) {
            popUpTo(RouterSet.LoginPresentation.name) {
                inclusive = true
            }
        }
    }
    SplashScreen()
}

@Composable
fun SplashScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.logo_look_out_name),
            contentDescription = stringResource(id = R.string.loading_logo),
            alignment = Alignment.Center,
            modifier = Modifier.size(150.dp)
        )
    }
}