package com.example.scdpredict.navigation

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
import com.google.android.gms.auth.api.identity.Identity
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scdpredict.screens.Home
import com.example.scdpredict.screens.Login
import com.example.scdpredict.screens.Register
import com.example.scdpredict.screens.animatedSplashScreen
import com.example.scdpredict.screens.Welcome
import com.example.sharedlibrary.data.email_password_sign_in.utils.AuthViewModel
import com.example.scdpredict.viewmodels.CRUDViewmodel
import com.example.sharedlibrary.data.google_sign_in.GoogleAuthUiClient
import com.example.sharedlibrary.data.google_sign_in.SignInViewmodel
import kotlinx.coroutines.launch


@Composable
fun Navigation(
    crudViewModel: CRUDViewmodel,
    //navController: NavHostController,
    authViewModel: AuthViewModel,
){
    val lifecycleScope = rememberCoroutineScope() //to include a coroutine in a composable function
    val context = LocalContext.current  //allows me to use application context in a composable
    val navController = rememberNavController()

    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = context,
            oneTapClient = Identity.getSignInClient(context)
        )
    }

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route)
    {
        composable(route = Screen.Splash.route){
            animatedSplashScreen(navController = navController)
        }

        composable(route = Screen.Welcome.route){
            Welcome(
                navController = navController,
                viewModel = crudViewModel)
        }
        composable(route = Screen.Login.route){
            val viewModel = viewModel<SignInViewmodel>()
            val state by viewModel.state.collectAsStateWithLifecycle()

            LaunchedEffect(key1 = Unit){
                if (googleAuthUiClient.getSignedInUser() != null){
                    navController.navigate(route = Screen.Home.route)
                }
            }

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if(result.resultCode == Activity.RESULT_OK){
                        lifecycleScope.launch{
                            val signInResult = googleAuthUiClient.signInWithIntent(
                                intent = result.data ?: return@launch
                            )
                            viewModel.onSignInResult(signInResult)
                        }
                    }
                }
            )

            LaunchedEffect(key1 = state.isSignInSuccessful){
                if(state.isSignInSuccessful){
                    Toast.makeText(
                        context,
                        "Sign in successful",
                        Toast.LENGTH_LONG
                    ).show()

                    navController.navigate(route = Screen.Home.route)
                    viewModel.resetState()
                }
            }
            Login(
                navController = navController,
                state = state,
                onGoogleSignInClick = {
                      lifecycleScope.launch {
                          val signInIntentSender = googleAuthUiClient.signIn()
                          launcher.launch(
                              IntentSenderRequest.Builder(
                                  signInIntentSender ?: return@launch
                              ).build()
                          )
                      }
                },
                authViewModel = authViewModel
            )
        }
        composable(route = Screen.Register.route){
            Register(
                navController = navController,
                viewModel = crudViewModel,
                authViewModel = authViewModel
            )
        }
        composable(route = Screen.Home.route){
            Home(
                navController = navController,
                authViewModel = authViewModel
            )
        }
    }
}