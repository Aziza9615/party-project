package com.example.authactivity.ui.main

import android.app.Activity
import android.content.Intent
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.util.*

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(MainViewModel::class) {

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = MainViewModel::class)
    }

    override fun subscribeToLiveData() {

    }

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, MainActivity::class.java)
            activity.startActivity(intent)
        }
    }
}