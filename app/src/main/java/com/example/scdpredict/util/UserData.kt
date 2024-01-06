package com.example.scdpredict.util

data class UserData(
    var userID: String = "",
    //var name: String = "",
    var email: String = "",
    //var age: Int = 0,
    //var gender: String = "",
    var password: String = ""
)
data class Vitals(
    var heartrate: String = "",
    var respirationRate: String = "",
    var painscore: String = ""
)