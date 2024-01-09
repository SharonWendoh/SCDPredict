package com.example.scdpredict.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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
import com.example.scdpredict.Components.BottomAppBar
import com.example.scdpredict.Components.MetricsCardView
import com.example.scdpredict.Components.ScoreCardView
import com.example.scdpredict.Components.TitleText
import com.example.scdpredict.Components.TopAppBar
import com.example.scdpredict.Components.TrackerCardView
import com.example.scdpredict.R
import com.example.scdpredict.navigation.Screen
import com.example.scdpredict.ui.theme.SCDPredictTheme
import com.example.scdpredict.util.Vitals
import com.example.scdpredict.viewmodels.CRUDViewmodel
import com.example.sharedlibrary.data.email_password_sign_in.utils.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    navController: NavController,
    authViewModel: AuthViewModel?,
    vitalsViewModel: CRUDViewmodel
){
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val userId = authViewModel?.currentUser?.uid

    // Remember the vitals data to trigger recomposition when the data is updated
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
        topBar = { TopAppBar(
            userName = authViewModel?.currentUser?.displayName ?: "",
            scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
            logoutOnClick = {
                authViewModel?.logout()
                navController.navigate(route = Screen.Login.route){
                    popUpTo(route = Screen.Home.route)
                }
            }
        )},
        bottomBar = { BottomAppBar(
            onHomeClick = {
                navController.navigate(route = Screen.Home.route)
            },
            onAddClick = {
                navController.navigate(route = Screen.Add.route)
            },
            onAccountClick = {
                navController.navigate(route = Screen.UserProfile.route)
            } )} ,

    )
    {
        values ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(values)
        ){
            items(1){
                index ->
                ScoreCardView(
                    vitalsData?.painscore?.toInt() ?:0,
                    "Wellness Score",
                    "Based on your data, we think your health status is above average")
                Spacer(modifier = Modifier.size(5.dp))
            }
            item {
                TitleText(
                    modifier = Modifier
                        .padding(10.dp, 5.dp),
                    text = "Smart Health Metrics" )
                LazyRow(){
                    item{
                        MetricsCardView(
                            vitalsData?.heartrate?.toInt() ?: 0, // Use the actual property names based on your Vitals data class
                            "Heart Rate",
                            "BPM",
                            icon = painterResource(id = R.drawable.pulse)
                        )
                    }
                    item{
                        MetricsCardView(
                            score =vitalsData?.diastolic?.toInt() ?: 0,
                            title = "Diastolic" ,
                            text = "bpm",
                            icon = painterResource(id = R.drawable.blood_pressure)
                        )
                    }
                    item{
                        MetricsCardView(
                            score =vitalsData?.systolic?.toInt() ?: 0,
                            title = "systolic" ,
                            text = "bpm",
                            icon = painterResource(id = R.drawable.blood_pressure)
                        )
                    }
                    item{
                        MetricsCardView(
                            score =vitalsData?.respirationRate?.toInt() ?: 0,
                            title = "Respiration Rate" ,
                            text = "bpm",
                            icon = painterResource(id = R.drawable.respiratory)
                        )
                    }
                    item{
                        MetricsCardView(
                            score =vitalsData?.spo2?.toInt() ?: 0,
                            title = "SPO2" ,
                            text = "bpm",
                            icon = painterResource(id = R.drawable.oxygen_concentration)
                        )
                    }
                    item{
                        MetricsCardView(
                            score =vitalsData?.temperature?.toInt() ?: 0,
                            title = "Temperature" ,
                            text = "bpm",
                            icon = painterResource(id = R.drawable.temperature)
                        )
                    }
                }
                TitleText(
                    modifier = Modifier
                        .padding(10.dp, 5.dp),
                    text = "Smart Health Tracker" )
            }
            items(4){
                    index ->
                TrackerCardView(
                    painterResource(id = R.drawable.graph),
                    "Medication",
                    0)
            }
        }
    }
}

@Preview
@Composable
fun HomePreview(){
    SCDPredictTheme {
        Home(
            navController = rememberNavController(),
            authViewModel = null,
            vitalsViewModel = CRUDViewmodel()
        )
    }
}