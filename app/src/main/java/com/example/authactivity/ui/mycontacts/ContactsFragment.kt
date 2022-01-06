package com.example.authactivity.ui.mycontacts

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.authactivity.base.BaseFragment
import com.example.authactivity.databinding.FragmentContactsBinding
import com.example.authactivity.model.ListData
import kotlinx.android.synthetic.main.item_contacts.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ContactsFragment : BaseFragment<ContactsViewModel, FragmentContactsBinding>(ContactsViewModel::class), ClickListener{

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
        viewModel = getViewModel(clazz = ContactsViewModel::class)
        setupRecyclerView()
        setupListeners()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getList()
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
//        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String): Boolean {
//                return false
//            }
//            override fun onQueryTextChange(newText: String): Boolean {
//                if (newText == "") adapter.addItems(viewModel.filteredList)
//                else {
//                    val searchText = newText.toLowerCase()
//                    val filtered = mutableListOf<ListData>()
//                    viewModel.filteredList.forEach {
//                        if (it.name.toLowerCase().contains(searchText)) filtered.add(it)
//                    }
//                    adapter.addItems(filtered)
//                }
//                return false
//            }
//        })
    }

    companion object {
        const val PRESENT_ITEM = "PRESENT_ITEM"
    }

    override fun subscribeToLiveData() {
        viewModel.data.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            adapter.addItems(it)
        })
    }

    override fun onItemClick(item: ListData) {
        newBtn.setOnClickListener {
            val intent = Intent(requireContext(), ContactsActivity::class.java)
            intent.putExtra(PRESENT_ITEM)
            startActivity(intent)
        }
    }

    override fun onLongItemClick(item: ListData) {
    }
}

fun Intent.putExtra(presentItem: String) {
}



