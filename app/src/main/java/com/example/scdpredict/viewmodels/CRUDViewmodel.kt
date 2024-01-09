package com.example.scdpredict.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.scdpredict.util.UserData
import com.example.scdpredict.util.Vitals
import com.example.sharedlibrary.data.email_password_sign_in.utils.await
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CRUDViewmodel(): ViewModel() {

    fun saveData(
        userData: UserData,
        context: Context
    ) = CoroutineScope(Dispatchers.IO).launch{

        val fireStoreRef = Firebase.firestore
            .collection("Users")
            .document(userData.userID)


        try {
            // Check if the document already exists
            val documentSnapshot = fireStoreRef.get().await()
            if (documentSnapshot.exists()) {
                // Document exists, update the data
                val updateData = mapOf(
                    "userID" to userData.userID,
                    "email" to userData.email,
                    "name" to userData.name,
                    "gender" to userData.gender,
                    "age" to userData.age
                    // Add other properties as needed
                )
                fireStoreRef.update(updateData)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Data successfully updated", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(context, "Error updating data: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            } else {
                // Document doesn't exist, save new data
                fireStoreRef.set(userData)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Data successfully saved", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(context, "Error saving data: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        } catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }
    fun saveVitalsData(
        userID: String,
        vitalsData: Vitals,
        context: Context
    ) = CoroutineScope(Dispatchers.IO).launch{

        val fireStoreRef = Firebase.firestore
            .collection("Users")
            .document(userID)
            .collection("vitals")

        // Create a document with random document id
        //var timestamp = FieldValue.serverTimestamp()
        val vitalsDocumentRef = fireStoreRef.document()

        try {
            // Add timestamp field to vitalsData
            val vitalsDataWithTimestamp = vitalsData.copy(timestamp = Timestamp.now())

            vitalsDocumentRef.set(vitalsDataWithTimestamp)
                .addOnSuccessListener {
                    Toast.makeText(context, "Vitals Data successfully saved", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }

        } catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }
    fun retrieveData(
        userID: String,
        context: Context,
        data: (UserData) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch{

        val fireStoreRef = Firebase.firestore
            .collection("Users")
            .document(userID)

        try {
            fireStoreRef.get()
                .addOnSuccessListener {
                    if (it.exists()){
                        val userData = it.toObject<UserData>()!!
                        data(userData)
                    } else {
                        Toast.makeText(context, "No user Data found", Toast.LENGTH_SHORT).show()
                    }
                }

        } catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }
    fun retrieveUserVitals(
        userID: String,
        context: Context,
        onData:(Vitals?) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch{

        val vitalsCollectionRef = Firebase.firestore
            .collection("Users")
            .document(userID)
            .collection("vitals")

        try {
            // Query the "vitals" subcollection to get documents sorted by timestamp in descending order
            vitalsCollectionRef
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .limit(1)
                .get()
                .addOnSuccessListener {QuerySnapshot ->
                    if (!QuerySnapshot.isEmpty){
                        // Retrieve the latest vitals document
                        val latestVitalsDocument = QuerySnapshot.documents[0]
                        val vitalsData = latestVitalsDocument.toObject<Vitals>()
                        onData(vitalsData)
                    } else {
                        // No vitals data found
                        onData(null)
                        Toast.makeText(context, "No vitals Data found", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }

        } catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }
    fun login(
        userID: String,
        email: String,
        password: String,
        context: Context,
        data: (UserData) -> Unit
    ){
        val fireStoreRef = Firebase.firestore
            .collection("user")
            .document(userID)
        try {
            fireStoreRef.get()
                .addOnSuccessListener {
                    if (it.exists()){
                        val userData = it.toObject<UserData>()!!
                        if(userData != null && userData.email == email && userData.password == password){
                            data(userData)
                        } else {
                            Toast.makeText(context, "Invalid email or password", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(context, "No user Data found", Toast.LENGTH_SHORT).show()
                    }
                }

        } catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }
    fun deleteData(
        userID: String,
        context: Context,
        navController: NavController,
        backToPreviousScreen: () -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch{

        val fireStoreRef = Firebase.firestore
            .collection("user")
            .document(userID)

        try {
            fireStoreRef.delete()
                .addOnSuccessListener {
                    Toast.makeText(context, "No user Data found", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                }

        } catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

}


