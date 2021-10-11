package com.example.authactivity.local

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.authactivity.R
import com.google.android.material.snackbar.Snackbar

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun showActionSnackbar(
        view: View,
        message: String,
        actionTitle: String,
        action: () -> Unit,
        context: Context
) {
    Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAction(actionTitle) {
        action()
    }.setActionTextColor(ContextCompat.getColor(context, R.color.design_default_color_on_primary)).show()
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