package com.example.authactivity.ui.main

import com.example.authactivity.base.BaseActivity
import androidx.lifecycle.Observer
import com.example.authactivity.base.BaseEvent
import com.example.authactivity.base.BaseViewModel
import com.example.authactivity.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainViewModel : BaseViewModel<BaseEvent>()
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(MainViewModel::class) {

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = MainViewModel::class)
    }

    override fun subscribeToLiveData() {
        viewModel.event.observe(this, Observer {
        })
    }
}
