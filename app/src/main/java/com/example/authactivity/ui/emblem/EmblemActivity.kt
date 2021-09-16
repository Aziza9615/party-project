package com.example.authactivity.ui.emblem

import android.content.Intent
import androidx.lifecycle.Observer
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityEmblemBinding
import com.example.authactivity.ui.language.LanguageActivity
import com.example.authactivity.ui.main.MainActivity
import com.example.authactivity.ui.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class EmblemActivity : BaseActivity<MainViewModel, ActivityEmblemBinding>(MainViewModel::class) {

    override fun getViewBinding() = ActivityEmblemBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = MainViewModel::class)
        setupListener()
    }

    private fun setupListener() {
        binding.btnNext.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.language.setOnClickListener {
            val intent = Intent(this, LanguageActivity::class.java)
            startActivity(intent)
        }
    }

    override fun subscribeToLiveData() {
    }
}