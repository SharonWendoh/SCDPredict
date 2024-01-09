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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import com.example.scdpredict.Components.ButtonWithRoundedCorner
import com.example.scdpredict.Components.RoundedTextField
import com.example.scdpredict.Components.ScoreCardView
import com.example.scdpredict.Components.SimpleSwitch
import com.example.scdpredict.Components.TextFieldLabel
import com.example.scdpredict.R
import com.example.scdpredict.navigation.Screen
import com.example.scdpredict.ui.theme.SCDPredictTheme
import com.example.scdpredict.util.Vitals
import com.example.scdpredict.viewmodels.CRUDViewmodel
import com.example.sharedlibrary.data.email_password_sign_in.utils.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelfReport(
    navController: NavController,
    authViewModel: AuthViewModel?,
    vitalsViewModel: CRUDViewmodel,
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val context = LocalContext.current
    val userId = authViewModel?.currentUser?.uid

    // Remember the vitals data to trigger recomposition when the data is updated
    var vitalsData by remember(userId) {
        mutableStateOf<Vitals?>(null)
    }

    var spo2: String by remember { mutableStateOf("") }
    var systolic: String by remember { mutableStateOf("") }
    var diastolic: String by remember { mutableStateOf("") }
    var heartrate: String by remember { mutableStateOf("") }
    var temperature: String by remember { mutableStateOf("") }
    var painscore: String by remember { mutableStateOf("") }
    var respirationRate: String by remember { mutableStateOf("") }
    //painscore = vitalsData?.painscore.toString()
    Scaffold (
        modifier = Modifier
            .fillMaxSize()
            //.nestedScroll(scrollBehavior.nestedScrollConnection)
        ,
        topBar = {
            BackNavigateTopAppBar(
                title = "New Self Report",
                navController = navController)
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
                    value = painscore,
                    onValueChange = {
                        painscore = it
                    },
                    icon = painterResource(id = R.drawable.graph))
                Spacer(modifier = Modifier.size(5.dp))
            }
            item {
                TextFieldLabel(
                    modifier = Modifier
                        .padding(8.dp,2.dp),
                    text = "Spo2" )
                RoundedTextField(
                    modifier = Modifier
                        .padding(8.dp,2.dp),
                    placeholder = "7",
                    value = spo2,
                    onValueChange = {
                        spo2 = it
                    },
                    icon = painterResource(id = R.drawable.graph))
                Spacer(modifier = Modifier.size(5.dp))
            }
            item {
                TextFieldLabel(
                    modifier = Modifier
                        .padding(8.dp,2.dp),
                    text = "Systolic" )
                RoundedTextField(
                    modifier = Modifier
                        .padding(8.dp,2.dp),
                    placeholder = "7",
                    value = systolic,
                    onValueChange = {
                        systolic = it
                    },
                    icon = painterResource(id = R.drawable.graph))
                Spacer(modifier = Modifier.size(5.dp))
            }
            item {
                TextFieldLabel(
                    modifier = Modifier
                        .padding(8.dp,2.dp),
                    text = "Diastolic" )
                RoundedTextField(
                    modifier = Modifier
                        .padding(8.dp,2.dp),
                    placeholder = "7",
                    value = diastolic,
                    onValueChange = {
                        diastolic = it
                    },
                    icon = painterResource(id = R.drawable.graph))
                Spacer(modifier = Modifier.size(5.dp))
            }
            item {
                TextFieldLabel(
                    modifier = Modifier
                        .padding(8.dp,2.dp),
                    text = "Heart Rate" )
                RoundedTextField(
                    modifier = Modifier
                        .padding(8.dp,2.dp),
                    placeholder = "7",
                    value = heartrate,
                    onValueChange = {
                        heartrate = it
                    },
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
                    value = temperature,
                    onValueChange = {
                        temperature = it
                    },
                    icon = painterResource(id = R.drawable.graph))
                Spacer(modifier = Modifier.size(5.dp))
            }
            item {
                TextFieldLabel(
                    modifier = Modifier
                        .padding(8.dp,2.dp),
                    text = "Respiration Rate" )
                RoundedTextField(
                    modifier = Modifier
                        .padding(8.dp,2.dp),
                    placeholder = "7",
                    value = respirationRate,
                    onValueChange = {
                        respirationRate = it
                    },
                    icon = painterResource(id = R.drawable.graph))
                Spacer(modifier = Modifier.size(5.dp))
            }
            item {
                ButtonWithRoundedCorner(
                    text = "Save",
                    modifier = Modifier,
                    onclick = {
                        val vitalsData = userId?.let {
                            Vitals(
                                spo2 = spo2,
                                systolic = systolic,
                                diastolic = diastolic,
                                heartrate = heartrate,
                                temperature = temperature,
                                painscore = painscore,
                                respirationRate = respirationRate
                            )
                        }
                        if (vitalsData != null) {
                            vitalsViewModel.saveVitalsData(userId,vitalsData, context )
                        }
                    })
            }
        }
    }
}
@Preview
@Composable
fun SelfReportPreview(){
    SCDPredictTheme {
        SelfReport(
            navController = rememberNavController(),
            authViewModel = null,
            vitalsViewModel = CRUDViewmodel(),
        )
    }
}