package com.example.authactivity.ui.settings

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import com.example.authactivity.R
import com.example.authactivity.local.SharedPrefModule

class ThemeActivity: AppCompatActivity() {

    private var xyz: Switch? = null
    internal lateinit var sharedPrefModule: SharedPrefModule

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPrefModule = SharedPrefModule(this)
        if (sharedPrefModule.loadNightModeState() == true) {
            setTheme(R.style.DarkTheme)
    } else
            setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        xyz = findViewById<View>(R.id.enableDark) as Switch?
        if (sharedPrefModule.loadNightModeState() == true) {
            xyz!!.isChecked = true
        }
        xyz!!.setOnCheckedChangeListener {
            buttonView, isChecked ->
            if (isChecked) {
                sharedPrefModule.setNightModeState(true)
                restartApp()
            }else{
                sharedPrefModule.setNightModeState(false)
                restartApp()
            }
        }
    }

    fun restartApp() {
        val intent = Intent(getApplicationContext(), ThemeActivity::class.java)
        startActivity(intent)
        finish()
    }
}