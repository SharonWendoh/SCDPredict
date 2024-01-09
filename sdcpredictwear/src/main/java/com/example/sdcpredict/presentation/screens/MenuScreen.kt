package com.example.sdcpredict.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.TimeText
import com.example.sdcpredict.R
import com.example.sdcpredict.presentation.components.NormalButton
import com.example.sdcpredict.presentation.components.NormalText
import com.example.sdcpredict.presentation.navigation.Screen
import com.example.sdcpredict.presentation.theme.SCDPredictTheme

@Composable
fun Menu(
    navController: NavController
){
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
                    NormalButton(
                        modifier = Modifier,
                        text = "Measure Heart Rate",
                        onButtonClick = { navController.navigate(Screen.WearApp.route)})
                }
                item {
                    NormalButton(
                        modifier = Modifier,
                        text = "Measure Oxygen Saturation",
                        onButtonClick = { navController.navigate(Screen.MeasureSpo2.route)})
                }
            }
        }
    }
}