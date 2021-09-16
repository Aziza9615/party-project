package com.example.authactivity.ui.language

import android.app.Activity
import android.content.Intent
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityLanguageBinding
import com.example.authactivity.ui.main.MainViewModel

class LanguageActivity : BaseActivity<MainViewModel, ActivityLanguageBinding>(MainViewModel::class) {

    override fun getViewBinding() = ActivityLanguageBinding.inflate(layoutInflater)

    override fun setupViews() {
    }

    override fun subscribeToLiveData() {
    }

    companion object {
        fun intent(activity: Activity) {
            val intent = Intent(activity, LanguageActivity::class.java)
            activity.startActivity(intent)
        }
    }
}
