package com.example.authactivity.ui.category

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.authactivity.R
import com.example.authactivity.base.BaseAddBottomSheetFragment
import com.example.authactivity.databinding.LayoutAddBottomBinding
import com.example.authactivity.local.isEmptyInputData
import com.example.authactivity.model.CategoryData
import com.example.authactivity.ui.mycontacts.ContactsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryBottomSheetFragment(contactsActivity: ContactsActivity) : BaseAddBottomSheetFragment(), AdapterCategory.CategoryClickListener {

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
        showAlertEdit()
    }

    private fun setupRecyclerView() {
        adapterCategory = AdapterCategory(this)
        val layoutManager: LinearLayoutManager = LinearLayoutManager(activity)
        layoutManager.setReverseLayout(true)
        layoutManager.setStackFromEnd(true)
        binding.recyclerView.setLayoutManager(layoutManager)
        binding.recyclerView.adapter = adapterCategory
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

    private fun setupListener() {
        binding.back.setOnClickListener { this.onDestroyView() }
    }

    override fun subscribeToLiveData() {
        viewModel.data.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            adapterCategory.addItems(it)
        })
    }

    override fun onCategoryClick(item: CategoryData) {

    }
}

