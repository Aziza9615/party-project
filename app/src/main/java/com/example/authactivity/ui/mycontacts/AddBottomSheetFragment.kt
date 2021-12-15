 package com.example.authactivity.ui.mycontacts

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.authactivity.R
import com.example.authactivity.base.BaseAddBottomSheetFragment
import com.example.authactivity.databinding.LayoutAddBottomSheetBinding
import com.example.authactivity.local.isEmptyInputData
import com.example.authactivity.model.ListData

 class AddBottomSheetFragment(contactsActivity: ContactsActivity) : BaseAddBottomSheetFragment() {

    lateinit var binding: LayoutAddBottomSheetBinding
     private lateinit var adapter: ContactAdapter
     private lateinit var viewModel: ContactsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutAddBottomSheetBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun setupViews() {
        setupListener()
        setupSearchView()
        showAlertEdit()
    }

     private fun setupSearchView() {
         binding.searchView.setOnSearchClickListener {

         }
         binding.searchView.setOnCloseListener {
             false
         }
     }

     override fun subscribeToLiveData() {

     }

     private fun showAlertEdit() {
         binding.add.setOnClickListener {
             val alert = AlertDialog.Builder(requireContext(), R.style.AddDialogStyle)

             val inflater = layoutInflater.inflate(R.layout.alert_edit, null)
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
             nameEditText: EditText, dialog: AlertDialog) {
         var error = 0
         if (nameEditText.isEmptyInputData(getString(R.string.add_name))) error += 1
         if (error > 0) return

         addItem(nameEditText, dialog)
     }

     fun addItem(nameEditText: EditText, dialog: AlertDialog) {
         val list = ListData(0, nameEditText.text.toString())
         dialog.dismiss()
         adapter.addItem(list)
         viewModel.insertList(list)
     }

     private fun setupListener() {
         binding.back.setOnClickListener { this.onDestroyView() }
        binding.newBtn.setOnClickListener {
        }
    }
}


