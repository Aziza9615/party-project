package com.example.authactivity.ui.lang

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.widget.Button
import com.example.authactivity.R
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityLangBinding
import com.example.authactivity.ui.onBoard.OnBoardActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.util.*

class LangActivity : BaseActivity<LangViewModel, ActivityLangBinding>(LangViewModel::class) {

    lateinit var russian : Button
    lateinit var kyrgyz : Button
    lateinit var english: Button

    override fun getViewBinding() = ActivityLangBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = LangViewModel::class)
        setupListener()
        loadLocate()
    }

    private fun setupListener() {
        russian = findViewById(R.id.btn_russian)
        kyrgyz = findViewById(R.id.btn_kyrgyz)
        english = findViewById(R.id.btn_english)
        russian.setOnClickListener {
                setLocate("ru")
                recreate()
            }

        kyrgyz.setOnClickListener {
                setLocate("ky")
                recreate()
            }
        english.setOnClickListener {
                setLocate("en")
            startActivity(Intent(this@LangActivity,OnBoardActivity::class.java))
                recreate()
            }
    }

    private fun setLocate(Lang: String?) {
        val locale = Locale(Lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        title = resources.getString(R.string.app_name)
        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", Lang)
        editor.apply()
    }

    private fun loadLocate() {
        val sharedPreferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val language = sharedPreferences.getString("My_Lang", "")
        setLocate(language)
    }

    override fun subscribeToLiveData() {
    }
}