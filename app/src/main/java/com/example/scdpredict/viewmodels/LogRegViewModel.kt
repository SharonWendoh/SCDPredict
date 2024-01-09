package com.example.scdpredict.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.scdpredict.util.UserData
import com.example.scdpredict.util.Vitals
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import org.tensorflow.lite.Interpreter;

class LogRegViewModel (private val context: Context) : ViewModel() {
    var tflite: Interpreter? = null
    private val _predictionResult = MutableStateFlow(20)
    val predictionResult: StateFlow<Int> = _predictionResult
    init {
        initializeInterpreter()
    }
    private fun initializeInterpreter() {
        try {
            tflite = Interpreter(loadModelFile())
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    fun performInference(inputStrings: List<String>) {
        // Convert input strings to float array
        val inputVal = FloatArray(inputStrings.size)
        for ((index, inputString) in inputStrings.withIndex()) {
            inputVal[index] = inputString.toFloatOrNull() ?: 0.0f
        }
        // Check if the interpreter is initialized
        if (tflite == null) {
            // Handle the case where the interpreter is not initialized
            // You might want to log an error or throw an exception
            _predictionResult.value = 0 // Notify observers with a default value
            return
        }

        val outputTensor = tflite?.getOutputTensor(0)
        val outputShape = outputTensor?.shape()
        val output = Array(outputShape?.get(0) ?: 0) { FloatArray(outputShape?.get(1) ?: 0) }

        try {
            tflite?.run(inputVal, output)

            // Assuming the output is a single float value
            val result = output[0][0].toInt()

            // Inside performInference function in LogRegViewModel
            Log.d("Prediction", "Performing inference with input: $inputVal")
            _predictionResult.value = result
        } catch (e: Exception){
            e.printStackTrace()
            _predictionResult.value = 0
        }
    }

    private fun loadModelFile(): ByteBuffer {
        val fileDescriptor = context.assets.openFd("sickle_cell_crisis_model.tflite")
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
            .order(ByteOrder.nativeOrder())
    }

    fun vitalsToList(vitals: Vitals): List<String>{
        return listOf(
            vitals.spo2.toString(),
            vitals.systolic.toString(),
            vitals.diastolic.toString(),
            vitals.heartrate.toString(),
            vitals.temperature.toString(),
            vitals.respirationRate.toString()
        )
    }

    fun userDataToList(userData: UserData): List<String>{
        return listOf(
            userData.age.toString(),
            userData.gender.toString()
        )
    }
}