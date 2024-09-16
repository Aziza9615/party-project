package com.example.authactivity.ui.tablayout.fragment

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.authactivity.base.BaseFragment
import com.example.authactivity.databinding.FragmentAcceptBinding
import com.example.authactivity.model.ContactData
import com.example.authactivity.ui.mycontacts.ContactViewModel
import com.example.authactivity.ui.tablayout.EditViewModel
import com.example.authactivity.ui.tablayout.adapter.EditAdapter
import org.koin.androidx.viewmodel.ext.android.getViewModel

class AcceptFragment() : BaseFragment<EditViewModel, FragmentAcceptBinding>(EditViewModel::class){

    private lateinit var adapter: EditAdapter

    override fun attachBinding(
        list: MutableList<FragmentAcceptBinding>,
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ) {
        list.add(FragmentAcceptBinding.inflate(layoutInflater, container, attachToRoot))
    }

    override fun setupViews() {
        viewModel = getViewModel(clazz = EditViewModel::class)
        setupRecyclerView()
        subscribe()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getEdit()
    }

    private fun setupRecyclerView() {
        adapter = EditAdapter()
        val layoutManager: LinearLayoutManager = LinearLayoutManager(activity)
        binding.recyclerView.setLayoutManager(layoutManager)
        binding.recyclerView.adapter = adapter
    }

    private fun subscribe() {
        viewModel.data.observe(viewLifecycleOwner,
            androidx.lifecycle.Observer { adapter.addItems(it) })
    }

    override fun subscribeToLiveData() {
        viewModel.data.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            adapter.addItems(it)
        })
    }
}

