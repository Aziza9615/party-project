package com.example.authactivity.ui.lang

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.widget.Button
import androidx.preference.PreferenceManager
import com.example.authactivity.R
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.base.BaseEvent
import com.example.authactivity.base.BaseViewModel
import com.example.authactivity.databinding.ActivityLangBinding
import com.example.authactivity.local.PrefsHelper
import com.example.authactivity.ui.main.MainActivity
import com.example.authactivity.ui.onBoard.OnBoardActivity
import com.example.authactivity.ui.onBoard.OnBoardViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.util.*

class LangActivity : BaseActivity<OnBoardViewModel, ActivityLangBinding>(OnBoardViewModel::class) {

    lateinit var russian: Button
    lateinit var kyrgyz: Button
    lateinit var english: Button

    override fun getViewBinding() = ActivityLangBinding.inflate(layoutInflater)

   // @SuppressLint("RestrictedApi")
    override fun setupViews() {
        viewModel = getViewModel(clazz = OnBoardViewModel::class)
        PrefsHelper.instance = PrefsHelper(this)
        if (PrefsHelper.instance.getGuest() == true) {
            //PreferenceManager(this).updateLanguage(PrefsHelper.instance.getLang()!!)
            startActivity(Intent(this@LangActivity, MainActivity::class.java))
        } else {
            setupListener()
        }
    }

    @SuppressLint("RestrictedApi")
    private fun setupListener() {
        russian = findViewById(R.id.btn_russian)
        kyrgyz = findViewById(R.id.btn_kyrgyz)
        english = findViewById(R.id.btn_english)
        russian.setOnClickListener {
            PreferenceManager(this).updateLanguage("ru")
            PrefsHelper.instance.saveGuest(true)
            PrefsHelper.instance.saveLang("ru")
            startActivity(Intent(this@LangActivity, OnBoardActivity::class.java))
            finish()
        }

        kyrgyz.setOnClickListener {
            PreferenceManager(this).updateLanguage("ky")
            PrefsHelper.instance.saveGuest(true)
            PrefsHelper.instance.saveLang("ky")
            startActivity(Intent(this@LangActivity, OnBoardActivity::class.java))
            finish()
        }
        english.setOnClickListener {
            PreferenceManager(this).updateLanguage("en")
            PrefsHelper.instance.saveGuest(true)
            PrefsHelper.instance.saveLang("en")
            startActivity(Intent(this@LangActivity, OnBoardActivity::class.java))
            finish()
        }
    }

    private fun PreferenceManager.updateLanguage(s: String) {
            val locale = Locale(s)
            Locale.setDefault(locale)
            val config = Configuration()
            config.locale = locale
            baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
            title = resources.getString(R.string.app_name)
            val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
            editor.putString("My_Lang", s)
            editor.apply()
        }

        override fun subscribeToLiveData() {}

        companion object {
            fun start(activity: Activity) {
                val intent = Intent(activity, LangActivity::class.java)
                activity.startActivity(intent)
            }
        }
    }
