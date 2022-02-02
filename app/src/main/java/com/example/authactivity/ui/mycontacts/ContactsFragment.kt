package com.example.authactivity.ui.mycontacts

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.authactivity.base.BaseFragment
import com.example.authactivity.databinding.FragmentContactsBinding
import com.example.authactivity.model.ListData
import com.example.authactivity.ui.mycontacts.bottomSheet.AddBottomSheetFragment
import com.example.authactivity.ui.tablayout.TabActivity
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.item_contacts.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ContactsFragment : BaseFragment<ListViewModel, FragmentContactsBinding>(  ListViewModel::class), ClickListener{

    lateinit var textView: TextView

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
        viewModel = getViewModel(clazz = ListViewModel::class)
        setupRecyclerView()
        setupListeners()
        setupSearchView()
        subscribe()
       // getIntentData()
    }

//    private fun getIntentData() {
//        val textView = binding.recyclerView
//        val intent = Intent()
//        val message = intent.getSerializableExtra("message") as ContactActivity.Message?
//        //textView.layoutManager = "$message"
//    }

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
            val intent = Intent(requireContext(), ContactActivity::class.java)
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
                    if (newText == "") adapter.addItems(viewModel.filteredList)
                    else {
                        val searchText = newText.toLowerCase()
                        val filtered = mutableListOf<ListData>()
                        viewModel.filteredList.forEach {
                            if (it.name?.toLowerCase()!!.contains(searchText)) filtered.add(it)
                        }
                        adapter.addItems(filtered)
                    }
                    return false
                }
            })
        }

    companion object {
        const val PRESENT_ITEM = "PRESENT_ITEM"
        const val PROJECT_KEY = "PROJECT_KEY"

        fun instance(context: Context, item: ListData) {
            val intent = Intent(context, ContactsFragment::class.java)
            intent.putExtra(PROJECT_KEY, item)
            context.startActivity(intent)
        }
    }

    override fun subscribeToLiveData() {
        viewModel.data.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            adapter.addItems(it)
        })
    }

    override fun onItemClick(item: ListData) {
        recycler_view.setOnClickListener {
            val intent = Intent(requireContext(), TabActivity::class.java)
            startActivity(intent)
        }
    }

    private fun subscribe() {
        viewModel.data.observe(viewLifecycleOwner, androidx.lifecycle.Observer { adapter.addItems(it) })
        viewModel.subscribeToData()
        viewModel.subscribeToMessage()
        viewModel.getList()
    }

    override fun onLongItemClick(item: ListData) {}
}

private fun Intent.putExtra(presentItem: String) {}




