package com.example.scdpredict.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.scdpredict.Components.BackNavigateTopAppBar
import com.example.scdpredict.Components.BottomAppBar
import com.example.scdpredict.Components.SimpleCardView
import com.example.scdpredict.Components.TopAppBar
import com.example.scdpredict.R
import com.example.scdpredict.ui.theme.SCDPredictTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    navController: NavController
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { BackNavigateTopAppBar(title = "New", navController = navController)
        },
        bottomBar = { BottomAppBar(onNavigationItemClick = {}) } ,

        ){
            values ->
        Column (
            modifier = Modifier
                .padding(values)
                .fillMaxSize(),

        ){
            SimpleCardView(
                painterResource(id = R.drawable.plus),
                "New Prediction")

            SimpleCardView(
                painterResource(id = R.drawable.plus),
                "New Self Report")
        }
    }

}

@Preview
@Composable
fun AddScreenPreview(){
    SCDPredictTheme {
        AddScreen(navController = rememberNavController())
    }
}