package com.example.authactivity.local

import android.content.Context
import android.content.SharedPreferences

class SharedPrefModule(context: Context) {

    internal lateinit var sharedPrefs: SharedPreferences

    init {
        sharedPrefs = context.getSharedPreferences("fileName", Context.MODE_PRIVATE)
    }

    fun setNightModeState(state: Boolean) {
        val editor: SharedPreferences.Editor = sharedPrefs.edit()
        editor.putBoolean("Night Mode", state!!)
        editor.commit()
    }

    fun loadNightModeState() : Boolean? {
        return sharedPrefs.getBoolean("Night Mode", false)
    }
}