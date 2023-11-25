package com.example.scdpredict.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.scdpredict.Components.ButtonWithRoundedCorner
import com.example.scdpredict.Components.NormalText
import com.example.scdpredict.Components.TitleText
import com.example.scdpredict.R
import com.example.scdpredict.navigation.Screen
import com.example.scdpredict.ui.theme.SCDPredictTheme
import com.example.scdpredict.viewmodels.CRUDViewmodel

@Composable
fun Welcome(
    navController: NavController,
    viewModel: CRUDViewmodel
){
    SCDPredictTheme() {
        Column (
            modifier = Modifier
                .fillMaxSize()
        ){
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxSize(),

                //contentAlignment = Alignment.Center
            ){
                Column (
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    //Spacer(modifier = Modifier.size(40.dp))
                    Image(
                        painter = painterResource(id =  R.drawable.cover),
                        contentDescription = "" ,
                        modifier = Modifier
                            .size(100.dp)
                    )
                    //Spacer(modifier = Modifier.size(40.dp))

                    TitleText(modifier = Modifier,
                        text = "Welcome to SCDPredict" )
                    //Spacer(modifier = Modifier.size(40.dp))

                    Image(
                        painter = painterResource(id = R.drawable.welcome_image),
                        contentDescription = "",
                        modifier = Modifier
                            .size(400.dp))
                    //Spacer(modifier = Modifier.size(40.dp))

                    ButtonWithRoundedCorner(
                        text = "Get Started  ->",
                        modifier = Modifier,
                        onclick = {navController.navigate(route = Screen.Register.route)}
                    )

                    NormalText(modifier =Modifier,
                        text = "Already have an account? Sign in.")
                }
            }
        }

    }


}

@Preview
@Composable
fun WelcomePreview(){
    SCDPredictTheme {
        Welcome(
            navController = rememberNavController(),
            viewModel = CRUDViewmodel()
        )
    }
}
