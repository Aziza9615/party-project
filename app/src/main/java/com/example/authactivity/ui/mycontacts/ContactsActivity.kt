package com.example.authactivity.ui.mycontacts

import android.content.Intent
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.authactivity.R
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityContactsBinding
import com.example.authactivity.databinding.ActivityMainBinding
import com.example.authactivity.ui.onBoard.OnBoardViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ContactsActivity : BaseActivity<ContactsViewModel, ActivityContactsBinding>(ContactsViewModel::class) {

    override fun getViewBinding() = ActivityContactsBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = ContactsViewModel::class)
        getIntentData()
    }

    private fun getIntentData() {
        val present = intent.getSerializableExtra(ContactsFragment.PRESENT_ITEM)
    }

    override fun subscribeToLiveData() {
    }
}