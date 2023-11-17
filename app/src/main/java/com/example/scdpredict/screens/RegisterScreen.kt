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
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
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
import com.example.scdpredict.util.UserData
import com.example.scdpredict.viewmodels.CRUDViewmodel

@Composable
fun Register(
    navController: NavController,
    viewModel: CRUDViewmodel

){
    var userID: String by remember { mutableStateOf("") }
    //var name: String by remember { mutableStateOf("") }
    var email: String by remember { mutableStateOf("") }
    var age: String by remember { mutableStateOf("") }
    var gender: String by remember { mutableStateOf("") }
    var password: String by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        LargeTopBox(
            title = "Sign Up For Free" ,
            text = "Get started in less than one minute and get your SCD monitoring personalized"
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
                placeholder = "enter your full names",
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
                placeholder = "enter your email address",
                value = email,
                onValueChange = {
                    email = it
                },
                icon = painterResource(id = R.drawable.mail)
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

            TextFieldLabel(
                modifier = Modifier,
                text = "Password Confirmation"
            )
            Spacer(modifier = Modifier.size(5.dp))
            RoundedTextField(
                modifier = Modifier,
                placeholder = "***********",
                value = "",
                onValueChange = {
                   // userID = it
                },
                icon = painterResource(id = R.drawable.lock)
            )
            Spacer(modifier = Modifier.size(15.dp))

            ButtonWithRoundedCorner(
                text = "Sign Up" ,
                modifier = Modifier,
                onclick = {
                    val userData = UserData(
                        userID = userID,
                        email = email,
                        password = password
                    )
                    viewModel.saveData(userData, context = context)
                    navController.navigate(route = Screen.Login.route)
                }
            )
            Spacer(modifier = Modifier.size(10.dp))

            NormalText(modifier = Modifier,
                text = "Already have an account? Sign in.")
        }

    }

}

@Preview
@Composable
fun RegisterPreview(){
    SCDPredictTheme {
        Register(
            navController = rememberNavController(),
            viewModel = CRUDViewmodel()
        )
    }
}
