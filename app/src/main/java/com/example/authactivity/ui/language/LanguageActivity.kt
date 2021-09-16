package com.example.authactivity.ui.language

import android.app.Activity
import android.content.Intent
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityLanguageBinding
import com.example.authactivity.ui.main.MainActivity
import com.example.authactivity.ui.main.MainViewModel

class LanguageActivity : BaseActivity<MainViewModel, ActivityLanguageBinding>(MainViewModel::class) {

    override fun getViewBinding() = ActivityLanguageBinding.inflate(layoutInflater)

    override fun setupViews() {
        setupListener()
    }

    private fun setupListener() {
        binding.btnNextLanguage.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun subscribeToLiveData() {}

    companion object {
        fun intent(activity: Activity) {
            val intent = Intent(activity, LanguageActivity::class.java)
            activity.startActivity(intent)
        }
    }
}
