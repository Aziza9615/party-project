package com.example.authactivity.ui.tablayout.fragment

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.authactivity.R
import com.example.authactivity.base.BaseFragment
import com.example.authactivity.databinding.FragmentAcceptBinding
import com.example.authactivity.databinding.FragmentGiveBinding
import com.example.authactivity.model.AcceptData
import com.example.authactivity.ui.mycontacts.ContactViewModel
import com.example.authactivity.ui.tablayout.adapter.AcceptAdapter
import kotlinx.android.synthetic.main.activity_contacts.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class GiveFragment : BaseFragment<AcceptViewModel, FragmentGiveBinding>(AcceptViewModel::class), AcceptAdapter.ClickListenerAccept {

    private lateinit var adapter: AcceptAdapter

    override fun attachBinding(
        list: MutableList<FragmentGiveBinding>,
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ) {
        list.add(FragmentGiveBinding.inflate(layoutInflater, container, attachToRoot))
    }

    override fun setupViews() {
        viewModel = getViewModel(clazz = AcceptViewModel::class)
        setupRecyclerView()
        subscribe()
    }


    private fun setupRecyclerView() {
        adapter = AcceptAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
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

    override fun onListClick(item: AcceptData) {}
    override fun onLongItemClickList(item: AcceptData) {}
}