package com.example.authactivity.ui.bottom

import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityDetailBinding
import com.example.authactivity.ui.bottom.list.ListViewModel

class DetailActivity : BaseActivity<ListViewModel, ActivityDetailBinding>(ListViewModel::class) {

    override fun getViewBinding() = ActivityDetailBinding.inflate(layoutInflater)

    override fun setupViews() {
        initViews()
        //setupListener()

    }

    private fun initViews() {

    }

    override fun subscribeToLiveData() {
    }

}