package com.example.authactivity.ui.bottom.detail_edit

import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityEditBinding
import com.example.authactivity.local.showToast
import com.example.authactivity.local.toSom
import com.example.authactivity.model.ListData
import com.example.authactivity.ui.bottom.list.ListActivity
import com.example.authactivity.ui.bottom.list.ListViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class EditListActivity : BaseActivity<ListViewModel, ActivityEditBinding>(ListViewModel::class) {

    private lateinit var listData: ListData
    override fun getViewBinding() = ActivityEditBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = ListViewModel::class)
        initViews()
        saveEdits()
        setupListener()
    }

    override fun subscribeToLiveData() {
    }

    private fun initViews() {
        val listData = intent.putExtra("list_detail", ListActivity.list_detail) as ListData
        binding.mTitleEdit.setText(listData.userName)
        binding.amountEdit.text = listData.userAm.toString().toSom()
        binding.mSubTitleEdit.setText(listData.userSub.toString())
    }

    private fun setupListener() {
        val listData = intent.putExtra("list_detail", ListActivity.list_detail) as ListData
        binding.btnEditSave.setOnClickListener {
            onBackPressed()
        }
        binding.btnEditCancel.setOnClickListener {
            onBackPressed()
        }
        binding.btnEditClose.setOnClickListener {
            onBackPressed()
        }
    }

    private fun saveEdits() {
        val newName = binding.mTitleEdit.text.toString()
        val newCategory = binding.mSubTitleEdit.text.toString()
        val newAmount = binding.amountEdit.text.toString()
        listData.userName = newName

        listData = ListData(newName, newCategory, newAmount)

        viewModel.fetchEdit()
        showToast(this, "Отредактировано")
    }
}
