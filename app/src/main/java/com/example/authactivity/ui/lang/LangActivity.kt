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
import com.example.authactivity.ui.onBoard.OnBoardActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.util.*

class LangViewModel : BaseViewModel<BaseEvent>()
class LangActivity : BaseActivity<LangViewModel, ActivityLangBinding>(LangViewModel::class) {

    lateinit var russian: Button
    lateinit var kyrgyz: Button
    lateinit var english: Button

    override fun getViewBinding() = ActivityLangBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = LangViewModel::class)
        setupListener()
    }

    @SuppressLint("RestrictedApi")
    private fun setupListener() {
        russian = findViewById(R.id.btn_russian)
        kyrgyz = findViewById(R.id.btn_kyrgyz)
        english = findViewById(R.id.btn_english)
        russian.setOnClickListener {
            PreferenceManager(this).updateLanguage("ru")
            startActivity(Intent(this@LangActivity, OnBoardActivity::class.java))
            finish()
        }
        kyrgyz.setOnClickListener {
            PreferenceManager(this).updateLanguage("ky")
            startActivity(Intent(this@LangActivity, OnBoardActivity::class.java))
            finish()
        }
        english.setOnClickListener {
            PreferenceManager(this).updateLanguage("en")
            startActivity(Intent(this@LangActivity, OnBoardActivity::class.java))
            finish()
        }
    }

    override fun subscribeToLiveData() {
    }

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, LangActivity::class.java)
            activity.startActivity(intent)
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
}
