package com.example.authactivity.local

import android.app.AlertDialog
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
fun showAlertDialog(context: Context, action: () -> Unit) {
    AlertDialog.Builder(context)
            .setTitle("Вы уверены")
            .setMessage("Вы хотите удалить все данные?")
            .setPositiveButton(
                    "Да"
            ) { dialog, _ ->
                action()
                dialog.dismiss()
            }
            .setNegativeButton("Нет", null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
}