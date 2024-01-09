package com.example.sdcpredict.presentation.navigation

sealed class Screen(val route: String){
    object Splash : Screen("splash_screen")
    object Welcome : Screen("welcome_screen")
    object Menu : Screen("menu_screen")
    object MeasureSpo2 : Screen("measure_spo2_screen")
    object WearApp : Screen("wear_app_screen")
    //object Register : Screen("register_screen")
    //object Home : Screen("home_screen")
}
