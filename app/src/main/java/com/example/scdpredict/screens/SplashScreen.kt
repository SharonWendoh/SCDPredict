package com.example.scdpredict.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.scdpredict.R
import com.example.scdpredict.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun animatedSplashScreen(navController: NavController){
    val scale = remember {
        Animatable(0f)
    }

    //This is a side effect handler. The Key could also be a state and whenever it changes,
    //the coroutine will rerun and the old one will be cancelled . By passing true, it means
    // the co routine will only happen once

    LaunchedEffect(key1 = true){
        scale.animateTo(
            targetValue = 1.2f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(3000L)
        navController.navigate(Screen.Welcome.route)
    }
    splash(
        modifier = Modifier.scale(scale.value)
    )
}

@Composable
fun splash(
    modifier: Modifier
){
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ){
        Image(
            modifier = Modifier
                .size(200.dp)
                .align(alignment = Alignment.Center)
                .then(modifier), 
            painter = painterResource(id = R.drawable.cover),
            contentDescription = ""
        )
    }
}