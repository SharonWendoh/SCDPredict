package com.example.scdpredict.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
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
import com.example.scdpredict.viewmodels.LogRegViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Prediction(
    navController: NavController,
    predictionViewModel: LogRegViewModel
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val predictionResult by predictionViewModel.predictionResult.collectAsState()

    Scaffold (
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { BackNavigateTopAppBar(title = "Prediction", navController = navController)
        },
        bottomBar = { BottomAppBar(onHomeClick = {},
            onAddClick = {
                navController.navigate(route = Screen.Add.route)
            },
            onNewsClick = {}) } ,
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
            items(4){
                    index ->
                TrackerCardView(
                    painterResource(id = R.drawable.graph),
                    "Medication",
                    "Up to date")
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
