package com.example.scdpredict.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.scdpredict.Components.BackNavigateTopAppBar
import com.example.scdpredict.Components.BottomAppBar
import com.example.scdpredict.Components.ButtonWithRoundedCorner
import com.example.scdpredict.Components.LargeCardView
import com.example.scdpredict.Components.RoundedTextField
import com.example.scdpredict.Components.TextFieldLabel
import com.example.scdpredict.Components.TrackerCardView
import com.example.scdpredict.R
import com.example.scdpredict.navigation.Screen
import com.example.scdpredict.ui.theme.SCDPredictTheme
import com.example.scdpredict.util.UserData
import com.example.scdpredict.viewmodels.CRUDViewmodel
import com.example.sharedlibrary.data.email_password_sign_in.utils.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfile(
    navController: NavController,
    authViewModel: AuthViewModel?,
    vitalsViewModel: CRUDViewmodel,
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val context = LocalContext.current
    val userId = authViewModel?.currentUser?.uid

    var userdata by remember(userId) {
        mutableStateOf<UserData?>(null)
    }
    var name: String by remember { mutableStateOf("") }
    name = authViewModel?.currentUser?.displayName ?: ""
    var email: String by remember { mutableStateOf("") }
    email = userdata?.email.toString()
    var password: String by remember { mutableStateOf("") }
    password = "************"
    var gender: String by remember { mutableStateOf("") }
    gender = userdata?.gender.toString()
    var age: String by remember { mutableStateOf("") }
    age = userdata?.age.toString()

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

    Scaffold (
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { BackNavigateTopAppBar(title = "Profile", navController = navController)
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
    ) { values ->
        PaddingValues(29.dp)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(values)
        ) {
                TextFieldLabel(
                    modifier = Modifier,
                    text = "Name"
                )
                Spacer(modifier = Modifier.size(5.dp))
                RoundedTextField(
                    modifier = Modifier,
                    placeholder = "names",
                    value = name,
                    onValueChange = {
                        name = it
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
                    placeholder = "password",
                    value = password,
                    onValueChange = {
                        // name = it
                    },
                    icon = painterResource(id = R.drawable.person)
                )
                Spacer(modifier = Modifier.size(15.dp))

                TextFieldLabel(
                    modifier = Modifier,
                    text = "Gender"
                )
                Spacer(modifier = Modifier.size(5.dp))
                RoundedTextField(
                    modifier = Modifier,
                    placeholder = "gender",
                    value = gender,
                    onValueChange = {
                         gender = it
                    },
                    icon = painterResource(id = R.drawable.person)
                )
                Spacer(modifier = Modifier.size(15.dp))

                TextFieldLabel(
                    modifier = Modifier,
                    text = "Age"
                )
                Spacer(modifier = Modifier.size(5.dp))
                RoundedTextField(
                    modifier = Modifier,
                    placeholder = "age",
                    value = age,
                    onValueChange = {
                         age = it
                    },
                    icon = painterResource(id = R.drawable.person)
                )
                Spacer(modifier = Modifier.size(15.dp))

                ButtonWithRoundedCorner(
                    text = "Update" ,
                    modifier = Modifier,
                    onclick = {
                        val userData = userId?.let {
                            UserData(
                                userID = it,
                                name = name,
                                email = email,
                                password = password,
                                gender = gender,
                                age = age
                            )
                        }
                        if (userData != null) {
                            vitalsViewModel.saveData(userData, context = context)
                        }
                        navController.navigate(route = Screen.UserProfile.route)
                    }
                )
                Spacer(modifier = Modifier.size(10.dp))

        }
    }
}

@Preview
@Composable
fun UserProfilePreview(){
    SCDPredictTheme {
        UserProfile(
            navController = rememberNavController(),
            authViewModel = null,
            vitalsViewModel = CRUDViewmodel(),
            )
    }
}