package com.example.sdcpredict.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.TimeText
import com.example.sdcpredict.presentation.theme.SCDPredictTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@Composable
fun loginScreen(){
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

            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Preview(
    device = Devices.WEAR_OS_SMALL_ROUND,
    showBackground = false,
    showSystemUi = true
)
@Composable
fun loginScreenPreview(){

}