package com.example.scdpredict.util

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue

data class UserData(
    var userID: String = "",
    var name: String = "",
    var email: String = "",
    var age: String = "",
    var gender: String = "",
    var password: String = ""
)
data class Vitals(
    var spo2: String = "",
    var systolic: String = "",
    var diastolic: String = "",
    var heartrate: String = "",
    var temperature: String = "",
    var painscore: String = "",
    var respirationRate: String = "",
    var timestamp: Timestamp? = null

)