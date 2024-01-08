package com.example.scdpredict.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.scdpredict.Components.BackNavigateTopAppBar
import com.example.scdpredict.Components.BottomAppBar
import com.example.scdpredict.Components.ButtonWithRoundedCorner
import com.example.scdpredict.Components.RoundedTextField
import com.example.scdpredict.Components.ScoreCardView
import com.example.scdpredict.Components.SimpleSwitch
import com.example.scdpredict.Components.TextFieldLabel
import com.example.scdpredict.R
import com.example.scdpredict.navigation.Screen
import com.example.scdpredict.ui.theme.SCDPredictTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelfReport(
    navController: NavController
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            BackNavigateTopAppBar(
                title = "New Self Report",
                navController = navController)
        },
        bottomBar = { BottomAppBar(
            onHomeClick = {},
            onAddClick = {
                navController.navigate(route = Screen.Add.route)
            },
            onNewsClick = {}) } ,
    ){
            values -> PaddingValues(8.dp)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(values)
        ){
            item {
                Row (
                    verticalAlignment = Alignment.Bottom
                ){
                    TextFieldLabel(
                        modifier = Modifier
                            .padding(8.dp,2.dp),
                        text = "In Pain?" )
                    SimpleSwitch()
                    Spacer(modifier = Modifier.size(5.dp))
                }
            }
            items(1){
                    index ->
                TextFieldLabel(
                    modifier = Modifier
                        .padding(8.dp,2.dp),
                    text = "Pain Score" )
                RoundedTextField(
                    modifier = Modifier
                        .padding(8.dp,2.dp),
                    placeholder = "7",
                    value = "",
                    onValueChange = {},
                    icon = painterResource(id = R.drawable.graph))
                Spacer(modifier = Modifier.size(5.dp))
            }
            item {
                TextFieldLabel(
                    modifier = Modifier
                        .padding(8.dp,2.dp),
                    text = "Blood Pressure" )
                RoundedTextField(
                    modifier = Modifier
                        .padding(8.dp,2.dp),
                    placeholder = "7",
                    value = "",
                    onValueChange = {},
                    icon = painterResource(id = R.drawable.graph))
                Spacer(modifier = Modifier.size(5.dp))
            }
            item {
                TextFieldLabel(
                    modifier = Modifier
                        .padding(8.dp,2.dp),
                    text = "Pulse" )
                RoundedTextField(
                    modifier = Modifier
                        .padding(8.dp,2.dp),
                    placeholder = "7",
                    value = "",
                    onValueChange = {},
                    icon = painterResource(id = R.drawable.graph))
                Spacer(modifier = Modifier.size(5.dp))
            }
            item {
                TextFieldLabel(
                    modifier = Modifier
                        .padding(8.dp,2.dp),
                    text = "Respiratory Rate" )
                RoundedTextField(
                    modifier = Modifier
                        .padding(8.dp,2.dp),
                    placeholder = "7",
                    value = "",
                    onValueChange = {},
                    icon = painterResource(id = R.drawable.graph))
                Spacer(modifier = Modifier.size(5.dp))
            }
            item {
                TextFieldLabel(
                    modifier = Modifier
                        .padding(8.dp,2.dp),
                    text = "Temperature" )
                RoundedTextField(
                    modifier = Modifier
                        .padding(8.dp,2.dp),
                    placeholder = "7",
                    value = "",
                    onValueChange = {},
                    icon = painterResource(id = R.drawable.graph))
                Spacer(modifier = Modifier.size(5.dp))
            }
            item {
                TextFieldLabel(
                    modifier = Modifier
                        .padding(8.dp,2.dp),
                    text = "Physical Activity" )
                RoundedTextField(
                    modifier = Modifier
                        .padding(8.dp,2.dp),
                    placeholder = "7",
                    value = "",
                    onValueChange = {},
                    icon = painterResource(id = R.drawable.graph))
                Spacer(modifier = Modifier.size(5.dp))
            }
            item {
                ButtonWithRoundedCorner(
                    text = "Save",
                    modifier = Modifier,
                    onclick = {})
            }
        }
    }
}
@Preview
@Composable
fun SelfReportPreview(){
    SCDPredictTheme {
        SelfReport(navController = rememberNavController())
    }
}