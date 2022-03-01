package com.example.authactivity.ui.tablayout.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.authactivity.base.BaseFragment
import com.example.authactivity.databinding.FragmentAcceptBinding
import com.example.authactivity.model.ContactData
import com.example.authactivity.model.EditData
import com.example.authactivity.ui.mycontacts.ContactViewModel
import com.example.authactivity.ui.tablayout.adapter.EditAdapter
import com.example.authactivity.ui.tablayout.bottomsheetEdit.EditViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class AcceptFragment : BaseFragment<EditViewModel, FragmentAcceptBinding>(EditViewModel::class), EditAdapter.ClickListenerAccept {

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
        //setupRecyclerView()
        subscribe()
        initViews()
    }

    private fun initViews() {
       // val contact = bundle.getSerializableExtra(ContactsFragment.name_detail) as ContactData
    }

//    private fun setupRecyclerView() {
//        adapter = EditAdapter(this)
//        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
//        binding.recyclerView.adapter = adapter
//    }

    private fun subscribe() {
        viewModel.data.observe(viewLifecycleOwner,
            androidx.lifecycle.Observer { adapter.addItems(it) })
    }

    override fun subscribeToLiveData() {
        viewModel.data.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            adapter.addItems(it)
        })
    }

    override fun onListClick(item: EditData) {}
    override fun onLongItemClickList(item: EditData) {}
}

