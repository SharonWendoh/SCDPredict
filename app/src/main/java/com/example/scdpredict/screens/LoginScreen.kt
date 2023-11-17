package com.example.scdpredict.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.scdpredict.Components.ButtonWithRoundedCorner
import com.example.scdpredict.Components.HorizontalLineWithText
import com.example.scdpredict.Components.IconBox
import com.example.scdpredict.Components.LargeTopBox
import com.example.scdpredict.Components.NormalText
import com.example.scdpredict.Components.RoundedTextField
import com.example.scdpredict.Components.TextFieldLabel
import com.example.scdpredict.R
import com.example.scdpredict.navigation.Screen
import com.example.scdpredict.ui.theme.SCDPredictTheme
import com.example.scdpredict.viewmodels.CRUDViewmodel

@Composable
fun Login(
    navController: NavController,
    viewModel: CRUDViewmodel
){
    var userID: String by remember { mutableStateOf("") }
    //var name: String by remember { mutableStateOf("") }
    var email: String by remember { mutableStateOf("") }
    //var age: String by remember { mutableStateOf("") }
    //var gender: String by remember { mutableStateOf("") }
    var password: String by remember { mutableStateOf("") }
    val context = LocalContext.current
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        LargeTopBox(
            title = "Sign In" ,
            text = "Sign in and get your SCD monitoring personalized with our ML technology"
        )
        Column (
            modifier = Modifier
                .padding(29.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            TextFieldLabel(
                modifier = Modifier,
                text = "Name"
            )
            Spacer(modifier = Modifier.size(5.dp))
            RoundedTextField(
                modifier = Modifier,
                placeholder = "example@gmail.com",
                value = userID,
                onValueChange = {
                    userID = it
                },
                icon = painterResource(id = R.drawable.person)
            )
            Spacer(modifier = Modifier.size(15.dp))

            TextFieldLabel(
                modifier = Modifier,
                text = "Email Address"
            )
            Spacer(modifier = Modifier.size(5.dp))
            RoundedTextField(
                modifier = Modifier,
                placeholder = "example@gmail.com",
                value = email,
                onValueChange = {
                    email = it
                },
                icon = painterResource(id = R.drawable.person)
            )
            Spacer(modifier = Modifier.size(15.dp))

            TextFieldLabel(
                modifier = Modifier,
                text = "Password"
            )
            Spacer(modifier = Modifier.size(5.dp))
            RoundedTextField(
                modifier = Modifier,
                placeholder = "***********",
                value = password,
                onValueChange = {
                                password = it
                },
                icon = painterResource(id = R.drawable.lock)
            )
            Spacer(modifier = Modifier.size(15.dp))

            ButtonWithRoundedCorner(
                text = "Sign In" ,
                modifier = Modifier,
                onclick = {
                    viewModel.login(
                        userID = userID,
                        email = email,
                        password = password,
                        context = context
                    ){
                        /*data ->
                        userID = data.userID
                        email = data.email
                        password = data.password*/
                    }
                    navController.navigate(route = Screen.Home.route)
                })
            Spacer(modifier = Modifier.size(10.dp))

            HorizontalLineWithText(
                text = "or" ,
                lineColor = MaterialTheme.colorScheme.primary,
                textColor = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.size(10.dp))

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(50.dp,6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ){
                IconBox(logo = painterResource(id = R.drawable.facebook))
                IconBox(logo = painterResource(id = R.drawable.google))
                IconBox(logo = painterResource(id = R.drawable.facebook))
            }
            Spacer(modifier = Modifier.size(15.dp))

            NormalText(modifier =Modifier,
                text = "Already have an account? Sign in.")
        }

    }

}

/*
@Preview
@Composable
fun LoginPreview(){
    SCDPredictTheme {
        Login()
    }
}*/
