package com.example.sdcpredict.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.TimeText
import com.example.sdcpredict.presentation.PERMISSION
import com.example.sdcpredict.presentation.data.HealthServicesRepository
import com.example.sdcpredict.presentation.theme.SCDPredictTheme
import com.example.sdcpredict.presentation.viewModel.MeasureDataViewModel
import com.example.sdcpredict.presentation.viewModel.MeasureDataViewModelFactory
import com.example.sdcpredict.presentation.viewModel.UiState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WearApp2(
    healthServicesRepository: HealthServicesRepository,
    //crudViewmodel: CRUDViewmodel
){
    var userID: String by remember { mutableStateOf("") }
    //var heartRate: String by remember { mutableStateOf("") }

    val context = LocalContext.current

    SCDPredictTheme {
        Scaffold (
            modifier = Modifier
                .fillMaxSize(),
            timeText = { TimeText() }
        ){
            val viewModel: MeasureDataViewModel = viewModel(
                factory = MeasureDataViewModelFactory(
                    healthServicesRepository = healthServicesRepository
                )
            )
            val enabled by viewModel.enabled.collectAsState()
            val spo2 by viewModel.spo2
            val availability by viewModel.availability
            val uiState by viewModel.uiState

            if (uiState == UiState.Supported){
                val permissionState = rememberPermissionState(
                    permission = PERMISSION,
                    onPermissionResult = { granted ->
                        if (granted) viewModel.toggleEnabled()
                    }
                )
                MeasureSpo2Screen(
                    spo2 = spo2,
                    availability = availability ,
                    enabled = enabled,
                    onButtonClick = { viewModel.toggleEnabled() },
                    permissionState = permissionState
                )
                /*val userVitals = UserVitals(
                    userID = userID,
                    heartRate = hr.toString()
                )
                crudViewmodel.saveData(userVitals, context = context)*/
            }else if (uiState == UiState.NotSupported) {

            }
        }
    }
}