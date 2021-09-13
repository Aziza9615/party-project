package com.example.authactivity.local

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun showSnackbar(
    view: View,
    message: String
){
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}

fun intentToNext(context: Context, clazz: Class<*>) {
    val intent = Intent(context, clazz)
    context.startActivity(intent)
}