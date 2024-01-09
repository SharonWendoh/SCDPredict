package com.example.scdpredict.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.scdpredict.Components.BackNavigateTopAppBar
import com.example.scdpredict.Components.BottomAppBar
import com.example.scdpredict.Components.LargeCardView
import com.example.scdpredict.Components.TrackerCardView
import com.example.scdpredict.R
import com.example.scdpredict.navigation.Screen
import com.example.scdpredict.ui.theme.SCDPredictTheme
import com.example.scdpredict.util.Vitals
import com.example.scdpredict.viewmodels.CRUDViewmodel
import com.example.scdpredict.viewmodels.LogRegViewModel
import com.example.sharedlibrary.data.email_password_sign_in.utils.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Prediction(
    navController: NavController,
    authViewModel: AuthViewModel?,
    vitalsViewModel: CRUDViewmodel,
    predictionViewModel: LogRegViewModel
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val context = LocalContext.current
    val userId = authViewModel?.currentUser?.uid
    val predictionResult by predictionViewModel.predictionResult.collectAsState()

    var vitalsData by remember(userId) {
        mutableStateOf<Vitals?>(null)
    }

    // Retrieve user vitals when the composable is first launched
    DisposableEffect(userId) {
        if (userId != null) {
            vitalsViewModel.retrieveUserVitals(
                userId,
                context = context  ) { vitals ->
                vitalsData = vitals
            }
        }

        // Cleanup effect
        onDispose { }
    }
    Scaffold (
        modifier = Modifier
            .fillMaxSize(),
        topBar = { BackNavigateTopAppBar(title = "Prediction", navController = navController)
        },
        bottomBar = { BottomAppBar(
            onHomeClick = {
                navController.navigate(route = Screen.Home.route)
            },
            onAddClick = {
                navController.navigate(route = Screen.Add.route)
            },
            onAccountClick = {
                navController.navigate(route = Screen.UserProfile.route)
            }) } ,
    ) { values ->
        PaddingValues(8.dp)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(values)
        ) {
            item{
                LargeCardView(

                    score = predictionResult
                )
            }
            item{
                TrackerCardView(
                    painterResource(id = R.drawable.graph),
                    "Heart Rate",
                    vitalsData?.heartrate?.toInt() ?: 0,)
            }
            item{
                TrackerCardView(
                    painterResource(id = R.drawable.graph),
                    "Diastolic",
                    vitalsData?.diastolic?.toInt() ?: 0,)
            }
            item{
                TrackerCardView(
                    painterResource(id = R.drawable.graph),
                    "Systolic",
                    vitalsData?.systolic?.toInt() ?: 0,)
            }
            item{
                TrackerCardView(
                    painterResource(id = R.drawable.graph),
                    "Respiration Rate",
                    vitalsData?.respirationRate?.toInt() ?: 0,)
            }
            item{
                TrackerCardView(
                    painterResource(id = R.drawable.graph),
                    "Oxygen Saturation",
                    vitalsData?.spo2?.toInt() ?: 0,)
            }
            item{
                TrackerCardView(
                    painterResource(id = R.drawable.graph),
                    "Temperature",
                    vitalsData?.temperature?.toInt() ?: 0,)
            }
        }
    }
}

/*
@Preview
@Composable
fun PredictionPreview(){
    SCDPredictTheme {
        Prediction(navController = rememberNavController(),
            predictionViewModel = null)
    }
}*/
