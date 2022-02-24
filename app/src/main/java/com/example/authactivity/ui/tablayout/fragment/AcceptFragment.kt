package com.example.authactivity.ui.tablayout.fragment

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.authactivity.base.BaseFragment
import com.example.authactivity.databinding.FragmentAcceptBinding
import com.example.authactivity.model.AcceptData
import com.example.authactivity.model.ContactData
import com.example.authactivity.ui.mycontacts.ContactViewModel
import com.example.authactivity.ui.mycontacts.ContactsFragment
import com.example.authactivity.ui.tablayout.TabActivity
import com.example.authactivity.ui.tablayout.adapter.AcceptAdapter
import kotlinx.android.synthetic.main.activity_contacts.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class AcceptFragment : BaseFragment<AcceptViewModel, FragmentAcceptBinding>(AcceptViewModel::class), AcceptAdapter.ClickListenerAccept {

    private lateinit var adapter: AcceptAdapter

    override fun attachBinding(
        list: MutableList<FragmentAcceptBinding>,
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ) {
        list.add(FragmentAcceptBinding.inflate(layoutInflater, container, attachToRoot))
    }

    override fun setupViews() {
        viewModel = getViewModel(clazz = AcceptViewModel::class)
        setupRecyclerView()
        subscribe()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAccept()
    }

    private fun initViews() {
        //val contact = intent.getSerializableExtra(ContactsFragment.name_detail) as ContactData
//        category.text = contact.category.toString()
//        amount.text = contact.amount.toString()
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
