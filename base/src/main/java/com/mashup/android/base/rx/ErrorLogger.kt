package com.mashup.android.base.rx

import android.util.Log

class ErrorLogger(private val message: String = "") : (Throwable) -> Unit {

    companion object {
        private const val TAG = "ErrorLogger"
    }

    override fun invoke(t: Throwable) {
        Log.e(TAG, message, t)
    }
}
