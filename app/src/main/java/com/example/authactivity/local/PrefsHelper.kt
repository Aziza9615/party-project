package com.example.authactivity.local

import android.content.Context
import android.content.SharedPreferences

class PrefsHelper(private val context: Context) {

    private val PREFS_NAME = "KowumchaApp"
    private val TOKEN = "TOKEN"
    private var prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    init {
        instance = this
    }

    fun saveToken(token: String?, refreshToken: String?) {
        prefs.edit().putString(TOKEN, token).apply()
    }

    fun getToken(): String {
        return prefs.getString(TOKEN, "") ?: ""
    }

    companion object {
        lateinit var instance: PrefsHelper
    }
}