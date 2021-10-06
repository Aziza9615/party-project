package com.example.authactivity.ui.emblem

import com.example.authactivity.R
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityEmblemBinding
import com.example.authactivity.ui.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class EmblemActivity : BaseActivity<MainViewModel, ActivityEmblemBinding>(MainViewModel::class) {

    override fun getViewBinding() = ActivityEmblemBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = MainViewModel::class)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, EmblemFragment()).commit()
    }

    override fun subscribeToLiveData() {
    }
}