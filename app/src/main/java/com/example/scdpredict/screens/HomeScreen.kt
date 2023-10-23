package com.example.scdpredict.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.scdpredict.Components.BottomAppBar
import com.example.scdpredict.Components.MetricsCardView
import com.example.scdpredict.Components.ScoreCardView
import com.example.scdpredict.Components.TitleText
import com.example.scdpredict.Components.TopAppBar
import com.example.scdpredict.Components.TrackerCardView
import com.example.scdpredict.R
import com.example.scdpredict.ui.theme.SCDPredictTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { TopAppBar(
            scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
        )},
        bottomBar = { BottomAppBar(onNavigationItemClick = {}) } ,

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
                    88,
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
                    items(5){
                        index ->
                        MetricsCardView(
                            88,
                            "Heart Rate",
                            "BPM",
                            icon = painterResource(id = R.drawable.pulse)
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
                    "Up to date")
            }
        }
    }
}

@Preview
@Composable
fun HomePreview(){
    SCDPredictTheme {
        Home()
    }
}