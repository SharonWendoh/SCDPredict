package com.example.scdpredict.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import org.tensorflow.lite.Interpreter;

class LogRegViewModel (private val context: Context) : ViewModel() {
    var tflite: Interpreter? = null
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

    fun performInference(inputStrings: List<String>): Int {
        // Convert input strings to float array
        val inputVal = FloatArray(inputStrings.size)
        for ((index, inputString) in inputStrings.withIndex()) {
            inputVal[index] = inputString.toFloatOrNull() ?: 0.0f
        }
        // Check if the interpreter is initialized
        if (tflite == null) {
            // Handle the case where the interpreter is not initialized
            // You might want to log an error or throw an exception
            return 0
        }

        val output = Array(1) { FloatArray(1) }
        tflite?.run(inputVal, output)
        // Assuming the output is a single float value
        val result = output[0][0].toInt()

        return result
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
}