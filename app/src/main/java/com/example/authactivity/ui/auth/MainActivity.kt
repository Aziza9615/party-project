package com.example.authactivity.ui.auth

import android.content.Intent
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityAuthBinding
import androidx.lifecycle.Observer
import com.example.authactivity.base.BaseEvent
import com.example.authactivity.base.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainViewModel : BaseViewModel<BaseEvent>()
class MainActivity : BaseActivity<MainViewModel, ActivityAuthBinding>(MainViewModel::class) {

    override fun getViewBinding() = ActivityAuthBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = MainViewModel::class)

    }

    override fun subscribeToLiveData() {
        viewModel.event.observe(this, Observer {
        })
    }
}
