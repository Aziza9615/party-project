package com.example.authactivity.ui.mycontacts

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.authactivity.base.BaseFragment
import com.example.authactivity.databinding.FragmentContactsBinding

class ContactsFragment : BaseFragment<ContactsViewModel, FragmentContactsBinding>(ContactsViewModel::class) {

    override fun setupViews() {
    }

    override fun subscribeToLiveData() {
    }

    override fun attachBinding(
            list: MutableList<FragmentContactsBinding>,
            layoutInflater: LayoutInflater,
            container: ViewGroup?,
            attachToRoot: Boolean
    ) {
        list.add(FragmentContactsBinding.inflate(layoutInflater, container, attachToRoot))
    }

}