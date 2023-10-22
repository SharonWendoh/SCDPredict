package com.example.scdpredict.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.scdpredict.Components.BottomAppBar
import com.example.scdpredict.Components.ScoreCardView
import com.example.scdpredict.Components.TopAppBar
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
            items(10){
                index ->
                ScoreCardView(
                    88,
                    "Wellness Score",
                    "Based on your data, we think your health status is above average")
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