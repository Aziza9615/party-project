package com.example.authactivity.ui.category

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatDrawableManager.get
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.authactivity.R
import com.example.authactivity.base.BaseAddBottomSheetFragment
import com.example.authactivity.databinding.LayoutAddBottomBinding
import com.example.authactivity.local.PrefsHelper
import com.example.authactivity.model.CategoryData
import com.example.authactivity.ui.mycontacts.ContactsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.reflect.Array.get

class CategoryBottomSheetFragment(contactsActivity: ContactsActivity) : BaseAddBottomSheetFragment(),
        CategoryClickListener {

    private val viewModel by viewModel<CategoryViewModel>()
    lateinit var binding: LayoutAddBottomBinding
    private lateinit var adapterCategory: AdapterCategory

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = LayoutAddBottomBinding.inflate(
                inflater, container, false
        )
        return binding.root
    }

    override fun setupViews() {
        setupListener()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapterCategory = AdapterCategory(this)
        val layoutManager: LinearLayoutManager = LinearLayoutManager(activity)
        layoutManager.setReverseLayout(true)
        layoutManager.setStackFromEnd(true)
        binding.recyclerView.setLayoutManager(layoutManager)
        binding.recyclerView.adapter = adapterCategory
    }

    private fun setupListener() {
        binding.back.setOnClickListener { this.onDestroyView() }

    }

    override fun subscribeToLiveData() {
        viewModel.data.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            adapterCategory.addItems(it)
        })
    }

    override fun onCategoryClick(item: CategoryData) {
//        val fragment = DetailPublicationFragment()
//        val bundle = Bundle()
//        bundle.putSerializable("category", item)
//        fragment.arguments = bundle
//        activity?.supportFragmentManager?.beginTransaction()?.add(R.id.main, fragment)?.addToBackStack(fragment.tag)?.commit()
//    }
    }
}

