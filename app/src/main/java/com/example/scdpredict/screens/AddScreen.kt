package com.example.scdpredict.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.scdpredict.Components.BackNavigateTopAppBar
import com.example.scdpredict.Components.BottomAppBar
import com.example.scdpredict.Components.SimpleCardView
import com.example.scdpredict.Components.TopAppBar
import com.example.scdpredict.R
import com.example.scdpredict.navigation.Screen
import com.example.scdpredict.ui.theme.SCDPredictTheme
import com.example.scdpredict.util.UserData
import com.example.scdpredict.util.Vitals
import com.example.scdpredict.viewmodels.CRUDViewmodel
import com.example.scdpredict.viewmodels.LogRegViewModel
import com.example.sharedlibrary.data.email_password_sign_in.utils.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(
    navController: NavController,
    authViewModel: AuthViewModel?,
    vitalsViewModel: CRUDViewmodel,
    predictionViewModel: LogRegViewModel?
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val context = LocalContext.current
    val userId = authViewModel?.currentUser?.uid

    var userdata by remember(userId) {
        mutableStateOf<UserData?>(null)
    }
    // Remember the vitals data to trigger recomposition when the data is updated
    var vitalsData by remember(userId) {
        mutableStateOf<Vitals?>(null)
    }
    // Retrieve user data when the composable is first launched
    DisposableEffect(userId) {
        if (userId != null) {
            vitalsViewModel.retrieveData(
                userId,
                context = context  ) { userData ->
                userdata = userData
            }
        }

        // Cleanup effect
        onDispose { }
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
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { BackNavigateTopAppBar(title = "New", navController = navController)
        },
        bottomBar = { BottomAppBar(
            onHomeClick = {},
            onAddClick = {
                navController.navigate(route = Screen.Add.route)
            },
            onNewsClick = {}) } ,

        ){
            values ->
        Column (
            modifier = Modifier
                .padding(values)
                .fillMaxSize(),

        ){
            SimpleCardView(
                painterResource(id = R.drawable.plus),
                "New Prediction",
                onClick = {
                    val inputStrings = vitalsData?.let { predictionViewModel?.vitalsToList(it)} ?: emptyList()
                    val userDataStrings = userdata?.let { predictionViewModel?.userDataToList(it) } ?: emptyList()
                    val mergedList: List<String> = userDataStrings + inputStrings
                    predictionViewModel?.performInference(mergedList)

                    navController.navigate(route = Screen.Prediction.route)
                })

            SimpleCardView(
                painterResource(id = R.drawable.plus),
                "New Self Report",
                onClick = {})
        }
    }

}

@Preview
@Composable
fun AddScreenPreview(){
    SCDPredictTheme {
        AddScreen(
            navController = rememberNavController(),
            authViewModel = null,
            vitalsViewModel = CRUDViewmodel(),
            predictionViewModel = null
            )
    }
}