package com.example.sharedlibrary.data.google_sign_in

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.example.sharedlibrary.R
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.tasks.Task
import com.google.android.gms.wearable.DataClient
import com.google.android.gms.wearable.DataItem
import com.google.android.gms.wearable.PutDataMapRequest
import com.google.android.gms.wearable.PutDataRequest
import com.google.android.gms.wearable.Wearable
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException

class GoogleAuthUiClient (
    private val context: Context,
    private val oneTapClient: SignInClient
){
    private val auth = Firebase.auth

    suspend fun signIn(): IntentSender?{
        val result = try {
            oneTapClient.beginSignIn(
                buildSignInRequest()
            ).await() //suspend the coroutine until the sign in is finished
        }
        catch (e: Exception){
            e.printStackTrace()
            if (e is CancellationException) throw e
            null
        }
        return result?.pendingIntent?.intentSender
    }

    // To get information from the intent that will be sent from firebase and make sense of that intent
    @SuppressLint("SuspiciousIndentation")
    suspend fun signInWithIntent(intent: Intent): SignInResult{
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)

        return try{
            val user = auth.signInWithCredential(googleCredentials).await().user
            val authTokenResult = user?.getIdToken(true)?.await()
            val dataClient: DataClient = Wearable.getDataClient(context)
            val putDataReq: PutDataRequest = PutDataMapRequest.create("/auth").run {
                if (authTokenResult != null) {
                    authTokenResult.token?.let { dataMap.putString("token", it) }
                }
                asPutDataRequest()
            }
            val putDataTask: Task<DataItem> = dataClient.putDataItem(putDataReq)

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
        } catch(e: Exception) {
            e.printStackTrace()
            if(e is CancellationException) throw e
            SignInResult(
                data = null,
                errorMessage = e.message
            )

        }
    }
    /*suspend fun sendAuthToken(token: String) {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)

        val user = auth.signInWithCredential(googleCredentials).await().user
        val authTokenResult = user?.getIdToken(true)?.await()
        // Code to send authentication token to Wearable device
    }*/

    suspend fun signOut(){
        try{
            oneTapClient.signOut().await()
            auth.signOut()
        }
        catch(e : Exception){
            e.printStackTrace()
            if(e is CancellationException) throw e
        }
    }

    suspend fun getSignedInUser(): UserData? {
        val currentUser = auth.currentUser ?: return null

        val authTokenResult = try {
            currentUser.getIdToken(true).await()
        } catch (e: Exception) {
            // Handle exceptions if needed
            null
        }

        return UserData(
            userId = currentUser.uid,
            username = currentUser.displayName,
            profilePictureUrl = currentUser.photoUrl?.toString(),
            //authToken = authTokenResult?.token
        )
    }
    /*fun getSignedInUser(): UserData? = auth.currentUser?.run{
        UserData(
            userId = uid,
            username = displayName,
            profilePictureUrl = photoUrl?.toString(),
            authToken =
        )
    }*/

    private fun buildSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(context.getString(R.string.web_client_id))
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
    }
}