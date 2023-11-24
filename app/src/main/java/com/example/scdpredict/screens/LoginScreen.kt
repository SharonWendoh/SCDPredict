package com.example.scdpredict.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.scdpredict.Components.ButtonWithRoundedCorner
import com.example.scdpredict.Components.HorizontalLineWithText
import com.example.scdpredict.Components.IconBox
import com.example.scdpredict.Components.LargeTopBox
import com.example.scdpredict.Components.LinkText
import com.example.scdpredict.Components.RoundedTextField
import com.example.scdpredict.Components.TextFieldLabel
import com.example.scdpredict.R
import com.example.sharedlibrary.data.Resource
import com.example.scdpredict.navigation.Screen
import com.example.scdpredict.ui.theme.SCDPredictTheme
import com.example.sharedlibrary.data.utils.AuthViewModel
import com.example.scdpredict.viewmodels.CRUDViewmodel
import com.example.sharedlibrary.data.google_sign_in.SignInState
import com.example.sharedlibrary.data.google_sign_in.SignInViewmodel

@Composable
fun Login(
    navController: NavController,
    //viewModel: CRUDViewmodel,
    onGoogleSignInClick: () -> Unit,
    state: SignInState,
    authViewModel: AuthViewModel?
){
    var userID: String by remember { mutableStateOf("") }
    var email: String by remember { mutableStateOf("") }
    var password: String by remember { mutableStateOf("") }
    val context = LocalContext.current

    val loginFlow = authViewModel?.loginFlow?.collectAsState()

    LaunchedEffect(key1 = state.signInError){
        state.signInError?.let{
                error -> Toast.makeText(context, error, Toast.LENGTH_LONG).show()
        }
    }

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
            /*TextFieldLabel(
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
            Spacer(modifier = Modifier.size(15.dp))*/

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
                    authViewModel?.login(email, password)
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
                    .padding(50.dp, 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ){
                IconBox(
                    logo = painterResource(id = R.drawable.facebook),
                    onClick = {})
                IconBox(
                    logo = painterResource(id = R.drawable.google),
                    onClick = {onGoogleSignInClick ()})
                IconBox(
                    logo = painterResource(id = R.drawable.facebook),
                    onClick = {})
            }
            Spacer(modifier = Modifier.size(15.dp))

            LinkText(
                modifier = Modifier,
                onclick = {
                    navController.navigate(route = Screen.Register.route)
                },
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary),
                    ) {
                        append("Don't have an account?")
                    }
                    withStyle(style = SpanStyle(
                        textDecoration = TextDecoration.Underline,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary)
                    ) {
                        append("Register.")
                    }
                }
            )
        }

        loginFlow?.value?.let {
            when(it){
                is Resource.Failure -> {
                    Toast.makeText(context, it.exception.message, Toast.LENGTH_LONG).show()
                }
                    Resource.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                is Resource.Success ->{
                    LaunchedEffect(Unit){
                        navController.navigate(route = Screen.Home.route){
                            //Prevents login screen from appearing again when back button is pressed
                            popUpTo(route = Screen.Home.route){ inclusive = true}
                        }
                    }
                }

            }
        }

    }

}

/*@Preview
@Composable
fun LoginPreview(){
    SCDPredictTheme {
        Login(
            navController = rememberNavController(),
            authViewModel = null,
            state = null,
            viewModel = CRUDViewmodel()
        )
    }
}*/
