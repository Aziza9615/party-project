package com.example.authactivity.ui.onBoard

import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityLangBinding
import com.example.authactivity.ui.lang.LangViewModel

class OnBoardActivity : BaseActivity<LangViewModel, ActivityLangBinding>(LangViewModel::class) {

    override fun getViewBinding() = ActivityLangBinding.inflate(layoutInflater)

    override fun setupViews() {
    }

    override fun subscribeToLiveData() {
    }

}