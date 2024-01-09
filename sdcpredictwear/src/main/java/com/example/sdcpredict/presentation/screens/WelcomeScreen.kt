package com.example.sdcpredict.presentation.screens

import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.example.sdcpredict.R
import com.example.sdcpredict.presentation.components.NormalButton
import com.example.sdcpredict.presentation.components.NormalText
import com.example.sdcpredict.presentation.navigation.Screen
import com.example.sdcpredict.presentation.theme.SCDPredictTheme
import com.example.sdcpredict.presentation.viewModel.AuthViewModel
import com.example.sharedlibrary.data.google_sign_in.SignInState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(navController: NavController){
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
        navController.navigate(Screen.Menu.route)
    }
    WelcomeScreen(
        modifier = Modifier.scale(scale.value),
        navController = rememberSwipeDismissableNavController()
    )
}
@Composable
fun WelcomeScreen(
    modifier: Modifier,
    navController: NavController,
    //authViewModel: AuthViewModel

    /*onGoogleSignInClick: () -> Unit,
    state: SignInState,*/
){
    //val authenticationState by authViewModel.authenticationState.observeAsState()
    val context = LocalContext.current

    /*LaunchedEffect(authViewModel) {
        val job = authViewModel.viewModelScope.launch {
            authViewModel.initWearableDataListener()
        }

        job.invokeOnCompletion { cause ->
            // Check if the coroutine completed successfully
            if (cause == null) {
                // Coroutine completed successfully, show a toast
                Toast.makeText(
                    context,
                    "Wearable data listener initialized successfully!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }*/
    /*LaunchedEffect(key1 = state.signInError){
        state.signInError?.let{
                error -> Toast.makeText(context, error, Toast.LENGTH_LONG).show()
        }
    }*/
    SCDPredictTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            timeText = { TimeText() }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                item {
                    Image(painter = painterResource(id =  R.drawable.blood_drop_by_mimooh),
                        contentDescription = "" ,
                        modifier = Modifier
                            .size(70.dp)
                    )
                }
                item {
                    NormalText(
                        modifier = Modifier
                            //.size(30.dp)
                                ,
                        text = "Welcome to SCDPredict")
                }
                /*item {
                    NormalButton(
                        modifier = Modifier,
                        text = "Sign in with google",
                        onButtonClick = { *//*onGoogleSignInClick ()*//*})
                }*/
            }
        }
    }
}

//@OptIn(ExperimentalPermissionsApi::class)
/*@Preview(
    device = Devices.WEAR_OS_SMALL_ROUND,
    showBackground = false,
    showSystemUi = true
)
@Composable
fun WelcomeScreenPreview(){
    WelcomeScreen(
        navController = ,
        authViewModel = AuthViewModel()
    )
}*/
