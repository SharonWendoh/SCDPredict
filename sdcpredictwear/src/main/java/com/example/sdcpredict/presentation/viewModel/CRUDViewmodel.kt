/*
package com.example.sdcpredict.presentation.viewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.sdcpredict.presentation.util.UserVitals
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CRUDViewmodel(): ViewModel() {

    fun saveData(
        userVitals: UserVitals,
        context: Context
    ) = CoroutineScope(Dispatchers.IO).launch{

        val fireStoreRef = Firebase.firestore
            .collection("vitals")
            .document(userVitals.userID)

        try {
            fireStoreRef.set(userVitals)
                .addOnSuccessListener {
                    Toast.makeText(context, "Data successfully saved", Toast.LENGTH_SHORT).show()
                }

        } catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}*/
