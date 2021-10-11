package com.example.authactivity.ui.bottom

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.authactivity.base.BaseFragment
import com.example.authactivity.databinding.FragmentHomeBinding
import com.example.authactivity.ui.bottom.list.ListActivity
import com.example.authactivity.ui.bottom.list.ListViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

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

//    private fun setupListener() {
//        binding.positiveBtn.setOnClickListener {
//            val intent = Intent(requireContext(), ListActivity::class.java)
//            intent.putExtra(NEXT_ITEM, Bundle())
//            startActivity(intent)
//        }
//    }
//
//    companion object {
//        const val NEXT_ITEM = " NEXT_ITEM"
//    }

    override fun setupViews() {
         viewModel = getViewModel(clazz = ListViewModel::class)
       // setupListener()
    }

    override fun subscribeToLiveData() {

    }
}