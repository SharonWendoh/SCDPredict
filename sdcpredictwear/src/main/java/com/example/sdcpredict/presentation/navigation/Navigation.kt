package com.example.sdcpredict.presentation.navigation

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.example.sdcpredict.presentation.screens.Splash
import com.example.sdcpredict.presentation.screens.WelcomeScreen
import com.example.sdcpredict.presentation.viewModel.AuthViewModel
import com.example.sharedlibrary.data.google_sign_in.GoogleAuthUiClient
import com.example.sharedlibrary.data.google_sign_in.SignInViewmodel
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.wearable.DataClient
import com.google.android.gms.wearable.Wearable
import kotlinx.coroutines.launch

@Composable
fun Navigation(){
    val lifecycleScope = rememberCoroutineScope() //to include a coroutine in a composable function
    val navcontroller = rememberSwipeDismissableNavController()
    val context = LocalContext.current
    val dataClient = Wearable.getDataClient(context)

    SwipeDismissableNavHost(
        navController = navcontroller,
        startDestination = Screen.Welcome.route
    ) {
        composable(Screen.Splash.route) {
            Splash()
        }

        composable(Screen.Welcome.route) {
            WelcomeScreen(
                navController = navcontroller,
                authViewModel = AuthViewModel(dataClient)
            )
        }
    }
}