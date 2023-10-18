package com.example.scdpredict.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scdpredict.screens.animatedSplashScreen
import com.example.scdpredict.screens.Welcome

@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route)
    {
        composable(route = Screen.Splash.route){
            animatedSplashScreen(navController = navController)
        }

        composable(route = Screen.Welcome.route){
            Welcome()
        }
    }
}