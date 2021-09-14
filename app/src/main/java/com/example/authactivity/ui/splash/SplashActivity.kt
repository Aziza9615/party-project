package com.example.authactivity.ui.splash

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import com.example.authactivity.R
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivitySplashBinding
import com.example.authactivity.local.PrefsHelper
import com.example.authactivity.ui.main.MainActivity
import com.example.authactivity.ui.main.MainViewModel

class SplashActivity : BaseActivity<MainViewModel, ActivitySplashBinding>
(MainViewModel::class) {

    override fun getViewBinding() = ActivitySplashBinding.inflate(layoutInflater)

    override fun setupViews() {
        setupAnimationText()
        Handler(Looper.getMainLooper()).postDelayed({ openActivity() }, 3000)
    }

    private fun setupAnimationText() {
        val animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        binding.emblem.startAnimation(animation)
    }

    private fun openActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun subscribeToLiveData() {}
}