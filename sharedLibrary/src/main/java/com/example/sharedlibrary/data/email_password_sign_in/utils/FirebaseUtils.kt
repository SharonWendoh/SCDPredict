package com.example.sharedlibrary.data.email_password_sign_in.utils

import com.google.android.gms.tasks.Task
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException

// We have created this to avoid using callbacks
//This is an extension function of task
suspend fun <T> Task<T>.await(): T{
    return suspendCancellableCoroutine {
        cont ->
        addOnCompleteListener{
            if(it.exception != null){
                cont.resumeWithException(it.exception!!)
            } else{
                cont.resume(it.result, null)
            }
        }
    }
}