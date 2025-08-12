package com.arielgos.mpos.core

import android.util.Log
import com.arielgos.mpos.TAG

class Logger {
    companion object {
        fun d(message: String) = Log.d(TAG, message)
        fun i(message: String) = Log.i(TAG, message)
        fun w(message: String) = Log.w(TAG, message)
        fun e(message: String, exception: Exception? = null) = Log.e(TAG, message, exception)
    }
}