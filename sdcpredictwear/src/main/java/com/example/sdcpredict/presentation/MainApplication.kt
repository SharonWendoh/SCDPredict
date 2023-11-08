package com.example.sdcpredict.presentation

import android.app.Application
import com.example.sdcpredict.presentation.data.HealthServicesRepository

const val TAG = "Measure Data Sample"
const val PERMISSION = android.Manifest.permission.BODY_SENSORS

class MainApplication : Application() {
    val healthServicesRepository by lazy { HealthServicesRepository(this) }
}