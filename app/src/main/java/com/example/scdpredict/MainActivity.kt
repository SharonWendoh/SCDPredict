package com.example.scdpredict

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.scdpredict.navigation.Navigation
import com.example.scdpredict.ui.theme.SCDPredictTheme
import com.example.sharedlibrary.data.email_password_sign_in.utils.AuthViewModel
import com.example.scdpredict.viewmodels.CRUDViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    //private lateinit var navController: NavHostController
    private val crudViewmodel: CRUDViewmodel by viewModels ()
    private val authViewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SCDPredictTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()

                Navigation(
                    crudViewModel = crudViewmodel,
                    //navController = navController,
                    authViewModel = authViewModel
                )
            }
        }
    }
}

