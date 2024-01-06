package com.example.sdcpredict.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sharedlibrary.data.email_password_sign_in.utils.await
import com.example.sharedlibrary.data.google_sign_in.SignInResult
import com.example.sharedlibrary.data.google_sign_in.SignInState
import com.example.sharedlibrary.data.google_sign_in.UserData
import com.google.android.gms.wearable.DataClient
import com.google.android.gms.wearable.DataEvent
import com.google.android.gms.wearable.DataMapItem
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.concurrent.CancellationException
import kotlin.coroutines.resume

class AuthViewModel(private val dataClient: DataClient) : ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun initWearableDataListener() {
        // Add listeners for data changes
        dataClient.addListener { dataEvents ->
            dataEvents.forEach { event ->
                if (event.type == DataEvent.TYPE_CHANGED) {
                    val dataItemPath = event.dataItem.uri.path ?: ""
                    if (dataItemPath.startsWith("/auth")) {
                        val token = DataMapItem.fromDataItem(event.dataItem).dataMap.getString("token")
                        // Handle the received token and perform authentication
                        if (token != null) {
                            viewModelScope.launch {
                                getSignedInUser(token)
                            }
                        }
                    }
                }
            }
        }
    }
    /*init {
        // Add listeners for data changes
        dataClient.addListener { dataEvents ->
            dataEvents.forEach { event ->
                if (event.type == DataEvent.TYPE_CHANGED) {
                    val dataItemPath = event.dataItem.uri.path ?: ""
                    if (dataItemPath.startsWith("/auth")) {
                        val token = DataMapItem.fromDataItem(event.dataItem).dataMap.getString("token")
                        // Handle the received token and perform authentication
                        if (token != null) {
                            viewModelScope.launch {
                                getUserData(token)
                            }
                        }
                    }
                }
            }
        }
    }*/

    @OptIn(ExperimentalCoroutinesApi::class)
    private suspend fun getSignedInUser(token: String): SignInResult {
        return try {
            val auth = FirebaseAuth.getInstance()
            val authResult = auth.signInWithCustomToken(token).await()
            val user = auth.currentUser
            SignInResult(
                data = user?.run{
                    UserData(
                        userId = uid,
                        username = displayName,
                        profilePictureUrl = photoUrl?.toString(),
                        //authToken = authTokenResult?.token
                    )
                },
                errorMessage = null
            )
        } catch (e: Exception){
            e.printStackTrace()
            if(e is CancellationException) throw e
            SignInResult(
                data = null,
                errorMessage = e.message
            )
        }
    }
    fun getUserData(SignInResult: SignInResult): UserData? {
        val currentUser = SignInResult.data
        return if (currentUser != null) {
            UserData(
                userId = currentUser.userId,
                username = currentUser.username,
                profilePictureUrl = currentUser.profilePictureUrl,
                //authToken = authTokenResult?.token
            )
        }
        else {
            // Handle the case where currentUser is null
            null
        }
    }
}