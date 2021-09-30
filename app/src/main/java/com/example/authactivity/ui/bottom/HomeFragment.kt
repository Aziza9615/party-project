package com.example.authactivity.ui.bottom

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.authactivity.base.BaseFragment
import com.example.authactivity.databinding.FragmentHomeBinding
import com.example.authactivity.databinding.FragmentListBinding
import com.example.authactivity.ui.bottom.viewmodel.ListViewModel

class HomeFragment : BaseFragment<ListViewModel, FragmentHomeBinding>(
    ListViewModel::class) {

    override fun attachBinding(
        list: MutableList<FragmentHomeBinding>,
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ) {
        list.add(FragmentHomeBinding.inflate(layoutInflater, container, attachToRoot))
    }

    override fun setupViews() {
       // viewModel = getViewModel(clazz = SubcategoryViewModel::class)
    }

    override fun subscribeToLiveData() {
        TODO("Not yet implemented")
    }
}