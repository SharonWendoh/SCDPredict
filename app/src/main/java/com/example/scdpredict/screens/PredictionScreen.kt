package com.example.scdpredict.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.scdpredict.Components.BackNavigateTopAppBar
import com.example.scdpredict.Components.BottomAppBar
import com.example.scdpredict.Components.LargeCardView
import com.example.scdpredict.Components.TrackerCardView
import com.example.scdpredict.R
import com.example.scdpredict.ui.theme.SCDPredictTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Prediction(){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { BackNavigateTopAppBar(title = "Prediction")
        },
        bottomBar = { BottomAppBar(onNavigationItemClick = {}) } ,
    ) { values ->
        PaddingValues(8.dp)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(values)
        ) {
            item{
                LargeCardView()
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

@Preview
@Composable
fun PredictionPreview(){
    SCDPredictTheme {
        Prediction()
    }
}