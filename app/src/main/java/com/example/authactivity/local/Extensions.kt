package com.example.authactivity.local

import android.widget.EditText

fun EditText.isEmptyInputData(message: String): Boolean {
    if (this.text.isNullOrEmpty()) {
        this.error = message
        return true
    }
    return false
}