package com.example.authactivity.ui.mycontacts.category

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.authactivity.R
import com.example.authactivity.base.BaseAddBottomSheetFragment
import com.example.authactivity.databinding.LayoutAddBottomBinding
import com.example.authactivity.local.PrefsHelper
import com.example.authactivity.local.isEmptyInputData
import com.example.authactivity.local.showAlertDone
import com.example.authactivity.model.CategoryData
import com.example.authactivity.ui.mycontacts.ContactActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

class CategoryBottomSheetFragment(contactsActivity: ContactActivity) : BaseAddBottomSheetFragment(), AdapterCategory.CategoryClickListener {

    lateinit var binding: LayoutAddBottomBinding
    private lateinit var adapterCategory: AdapterCategory
    private lateinit var viewModel: CategoryViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = LayoutAddBottomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupViews() {
        viewModel = getViewModel(clazz = CategoryViewModel::class)
        PrefsHelper.instance = PrefsHelper(requireContext())
        setupListener()
        setupRecyclerView()
        showAlertEdit()
        setupSearchView()
        subscribe()
    }

    private fun setupRecyclerView() {
        adapterCategory = AdapterCategory(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapterCategory
    }

    private fun subscribe() {
        viewModel.data.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            adapterCategory.addItems(it)
        })
        viewModel.subscribeToData()
        viewModel.subscribeToMessage()
        viewModel.getCategory()
    }

    private fun showAlertEdit() {
        binding.add.setOnClickListener {
            val alert = AlertDialog.Builder(requireContext(), R.style.AddDialogStyle)

            val inflater = layoutInflater.inflate(R.layout.alert_category, null)
            alert.setView(inflater)
            val negativeButton: Button = inflater.findViewById(R.id.btn_no)
            val positiveButton: Button = inflater.findViewById(R.id.btn_yes)

            val nameEditText: EditText = inflater.findViewById(R.id.et_name)

            val dialog = alert.create()

            negativeButton.setOnClickListener {
                dialog.dismiss()
            }
            positiveButton.setOnClickListener {
                checkField(nameEditText, dialog)
                showAlertDone(requireContext(), layoutInflater, R.layout.alert_done)
            }
            dialog.show()
        }
    }

    private fun checkField(
        nameEditText: EditText, dialog: AlertDialog
    ) {
        var error = 0
        if (nameEditText.isEmptyInputData(getString(R.string.add_name))) error += 1
        if (error > 0) return

        addItem(nameEditText, dialog)
    }

    fun addItem(nameEditText: EditText, dialog: AlertDialog) {
        val category = CategoryData(0, nameEditText.text.toString())
        dialog.dismiss()
        adapterCategory.addItem(category)
        viewModel.insertCategory(category)
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText == "") adapterCategory.addItems(viewModel.filteredCategory)
                else {
                    val searchText = newText.toLowerCase()
                    val filtered = mutableListOf<CategoryData>()
                    viewModel.filteredCategory.forEach {
                        if (it.category?.toLowerCase()!!.contains(searchText)) filtered.add(it)
                    }
                    adapterCategory.addItems(filtered)
                }
                return false
            }
        })
    }

    private fun setupListener() {
        binding.back.setOnClickListener { this.onDestroyView() }
    }
    companion object{
        const val CATEGORY_KEY = "CATEGORY_KEY"
    }

    override fun getTheme(): Int {
        return R.style.RoundedCornerBottomSheetDialog
    }

    override fun onCategoryClick(item: CategoryData) {
        val intent = Intent(requireContext(), ContactActivity::class.java)
        PrefsHelper.instance.saveCategory(item.category)
        startActivity(intent)
    }

    override fun onLongItemClickBottom(item: CategoryData) {}
}

