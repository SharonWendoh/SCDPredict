package com.example.scdpredict.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scdpredict.screens.Login
import com.example.scdpredict.screens.Register
import com.example.scdpredict.screens.animatedSplashScreen
import com.example.scdpredict.screens.Welcome
import com.example.scdpredict.viewmodels.CRUDViewmodel

@Composable
fun Navigation(
    crudViewModel: CRUDViewmodel,
    navController: NavHostController
){
   // val navController = rememberNavController()


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
            Login(
                navController = navController,
                viewModel = crudViewModel)
        }
        composable(route = Screen.Register.route){
            Register(
                navController = navController,
                viewModel = crudViewModel )
        }
    }
}