package com.example.authactivity.ui.tablayout.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.authactivity.base.BaseFragment
import com.example.authactivity.databinding.FragmentAcceptBinding
import com.example.authactivity.ui.mycontacts.ContactViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class AcceptFragment : BaseFragment<ContactViewModel, FragmentAcceptBinding>(ContactViewModel::class) {

    override fun setupViews() {
        viewModel = getViewModel (clazz = ContactViewModel::class)
    }

    override fun subscribeToLiveData() {
    }

    override fun attachBinding(
        list: MutableList<FragmentAcceptBinding>,
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ) {
        list.add(FragmentAcceptBinding.inflate(layoutInflater, container, attachToRoot))
    }
}