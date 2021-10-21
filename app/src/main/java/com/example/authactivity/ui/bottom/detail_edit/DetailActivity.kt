package com.example.authactivity.ui.bottom.detail_edit

import android.content.Intent
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityDetailBinding
import com.example.authactivity.local.toSom
import com.example.authactivity.model.ListData
import com.example.authactivity.ui.bottom.list.ListActivity.Companion.list_detail
import com.example.authactivity.ui.bottom.list.ListViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class DetailActivity : BaseActivity<ListViewModel, ActivityDetailBinding>(ListViewModel::class) {

    override fun getViewBinding() = ActivityDetailBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = ListViewModel::class)
        viewModel.fetchDetail()
        initViews()
        setupListener()
    }

    private fun initViews() {
        val listData = intent.putExtra("list_detail", list_detail) as ListData
        binding.mTitleDetail.text = listData.userName
        binding.amountDetail.text = listData.userAm.toString().toSom()
        binding.mSubTitleDetail.text = listData.userSub.toString()
    }

    private fun setupListener() {
        val listData = intent.putExtra("list_detail", list_detail) as ListData
        binding.btnLeftDetail.setOnClickListener {
            onBackPressed()
        }

        binding.btnDetailEdit.setOnClickListener {
            val intent = Intent(this, EditListActivity::class.java)
            intent.putExtra(list_detail, listData)
            startActivity(intent)
            finish()
        }
    }

    override fun subscribeToLiveData() {
    }
}