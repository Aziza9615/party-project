package com.example.authactivity.ui.mycontacts

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.authactivity.base.BaseFragment
import com.example.authactivity.databinding.FragmentContactsBinding
import com.example.authactivity.local.PrefsHelper
import com.example.authactivity.model.ContactData
import com.example.authactivity.ui.tablayout.TabActivity
import kotlinx.android.synthetic.main.item_adapter_bottom_sheet.newBtn
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ContactsFragment : BaseFragment<ContactViewModel, FragmentContactsBinding>(ContactViewModel::class), ClickListener {

    private lateinit var adapter: ContactAdapter
    private lateinit var contact: ContactData

    override fun attachBinding(
        list: MutableList<FragmentContactsBinding>,
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ) {
        list.add(FragmentContactsBinding.inflate(layoutInflater, container, attachToRoot))
    }

    override fun setupViews() {
        viewModel = getViewModel(clazz = ContactViewModel::class)
        setupRecyclerView()
        setupListeners()
        setupSearchView()
        subscribe()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getContact()
    }

    private fun setupRecyclerView() {
        adapter = ContactAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun setupListeners() {
        binding.btnAdd.setOnClickListener {
            val intent = Intent(requireContext(), ContactActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                if (newText == "") adapter.addItems(viewModel.filteredContact)
                else {
                    val searchText = newText.toLowerCase()
                    val filtered = mutableListOf<ContactData>()
                    viewModel.filteredContact.forEach {
                        if (it.name?.toLowerCase()!!.contains(searchText)) filtered.add(it)
                    }
                    adapter.addItems(filtered)
                }
                return false
            }
        })
    }

    private fun subscribe() {
        viewModel.data.observe(viewLifecycleOwner, androidx.lifecycle.Observer { adapter.addItems(it) })
        viewModel.subscribeToData()
        viewModel.subscribeToMessage()
        viewModel.getContact()
    }

    override fun subscribeToLiveData() {
        viewModel.data.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            adapter.addItems(it)
        })
    }

    override fun onItemClick(item: ContactData) {
        val intent = Intent(requireContext(), TabActivity::class.java)
        intent.putExtra(name_detail, item)
        startActivity(intent)
    }

    companion object {
        val name_detail = "NAME_DETAIL"
    }

    override fun onLongItemClick(item: ContactData) {
        newBtn.setOnClickListener {
            val intent = Intent(requireContext(), ContactActivity::class.java)
            intent.putExtra(name_detail, item)
            startActivity(intent)
        }
    }

    override fun onButtonClick(item: ContactData) {
    }
}



