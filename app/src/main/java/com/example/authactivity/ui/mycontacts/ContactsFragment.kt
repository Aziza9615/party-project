package com.example.authactivity.ui.mycontacts

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.authactivity.base.BaseFragment
import com.example.authactivity.databinding.FragmentContactsBinding
import com.example.authactivity.model.ContactData
import com.example.authactivity.model.ListData
import kotlinx.android.synthetic.main.item_contacts.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ContactsFragment : BaseFragment<ContactViewModel, FragmentContactsBinding>(ContactViewModel::class), ClickListener{

    private lateinit var adapter: ContactAdapter

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
            val intent = Intent(requireContext(), ContactsActivity::class.java)
            intent.putExtra(PRESENT_ITEM)
            startActivity(intent)
        }
    }

        private fun setupSearchView() {
            binding.searchView.setOnQueryTextListener(object :
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    if (newText == "") adapter.addItems(viewModel.filteredContact)
                    else {
                        val searchText = newText.toLowerCase()
                        val filtered = mutableListOf<ContactData>()
                        viewModel.filteredContact.forEach {
                            if (it.name.toLowerCase().contains(searchText)) filtered.add(it)
                        }
                        adapter.addItems(filtered)
                    }
                    return false
                }
            })
        }

    companion object {
        const val PRESENT_ITEM = "PRESENT_ITEM"
    }

    override fun subscribeToLiveData() {
        viewModel.data.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            adapter.addItems(it)
        })
    }

    override fun onItemClick(item: ContactData) {
        newBtn.setOnClickListener {
            val intent = Intent(requireContext(), ContactsActivity::class.java)
            intent.putExtra(PRESENT_ITEM)
            startActivity(intent)
        }
    }

    private fun subscribe() {
        viewModel.data.observe(viewLifecycleOwner, androidx.lifecycle.Observer { adapter.addItems(it) })
        viewModel.subscribeToData()
        viewModel.subscribeToMessage()
        viewModel.getContact()
    }

    override fun onLongItemClick(item: ContactData) {}
}

fun Intent.putExtra(presentItem: String) {
}




