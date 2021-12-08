package com.example.authactivity.ui.mycontacts.present

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.authactivity.base.BaseFragment
import com.example.authactivity.databinding.FragmentPresentBinding
import com.example.authactivity.ui.mycontacts.ContactsViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

//class PresentFragment : BaseFragment<ContactsViewModel, FragmentPresentBinding>(ContactsViewModel::class) {
//
//    override fun attachBinding(
//        list: MutableList<FragmentPresentBinding>,
//        layoutInflater: LayoutInflater,
//        container: ViewGroup?,
//        attachToRoot: Boolean
//    ) {
//        list.add(FragmentPresentBinding.inflate(layoutInflater, container, attachToRoot))
//    }
//
//    override fun setupViews() {
//        viewModel = getViewModel(clazz = ContactsViewModel::class)
//    }
//
//    override fun subscribeToLiveData() {
//
//    }
//}