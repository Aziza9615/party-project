package com.example.authactivity.ui.mycontacts

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.authactivity.base.BaseFragment
import com.example.authactivity.databinding.FragmentContactsBinding
import com.example.authactivity.model.ListData
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ContactsFragment : BaseFragment<ContactsViewModel, FragmentContactsBinding>(ContactsViewModel::class), ContactAdapter.ClickListener {

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
        setupListeners()
        setupRecyclerView()
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

    companion object {
        const val PRESENT_ITEM = "PRESENT_ITEM"
    }

    override fun subscribeToLiveData() {
    }

    override fun onClick(item: ListData) {
    }
}

private fun Intent.putExtra(presentItem: String) {
}



