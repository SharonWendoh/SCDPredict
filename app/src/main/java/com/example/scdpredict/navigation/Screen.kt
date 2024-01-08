package com.example.scdpredict.navigation

sealed class Screen(val route: String){
    object Splash : Screen("splash_screen")
    object Welcome : Screen("welcome_screen")
    object Login : Screen("login_screen")
    object Register : Screen("register_screen")
    object Home : Screen("home_screen")
    object Prediction : Screen("prediction_screen")
    object SelfReport : Screen("self_report_screen")
    object Add : Screen("add_screen")
}
