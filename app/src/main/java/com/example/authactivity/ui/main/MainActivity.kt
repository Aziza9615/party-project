package com.example.authactivity.ui.main

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
}