 package com.example.authactivity.ui.mycontacts.bottomSheet

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.authactivity.R
import com.example.authactivity.base.BaseAddBottomSheetFragment
import com.example.authactivity.databinding.LayoutAddBottomSheetBinding
import com.example.authactivity.local.isEmptyInputData
import com.example.authactivity.model.ListData
import com.example.authactivity.ui.mycontacts.*
import kotlinx.android.synthetic.main.activity_contacts.*
import kotlinx.android.synthetic.main.item_bottom_sheet.*
import kotlinx.android.synthetic.main.item_contacts_bottom_sheet.*
import kotlinx.android.synthetic.main.item_fragment_contacts.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import android.content.Intent as Intent1

 class AddBottomSheetFragment(contactsActivity: ContactsActivity) : BaseAddBottomSheetFragment(), ClickListenerBottom {

     lateinit var binding: LayoutAddBottomSheetBinding
     private lateinit var adapter: AdapterBottomSheet
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
         viewModel = getViewModel(clazz = ContactsViewModel::class)
         setupListener()
         setupSearchView()
         showAlertEdit()
         setupRecyclerView()
         subscribe()
     }

     private fun setupRecyclerView() {
         adapter = AdapterBottomSheet(this)
         binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
         binding.recyclerView.adapter = adapter
     }

     override fun subscribeToLiveData() {}

     private fun subscribe() {
         viewModel.data.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
             adapter.addItems(it)
         })
         viewModel.subscribeToData()
         viewModel.subscribeToMessage()
         viewModel.getList()
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
         nameEditText: EditText, dialog: AlertDialog
     ) {
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

     private fun setupSearchView() {
         binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
             override fun onQueryTextSubmit(query: String): Boolean {
                 return false
             }

             override fun onQueryTextChange(newText: String): Boolean {
                 if (newText == "") adapter.addItems(viewModel.filteredList)
                 else {
                     val searchText = newText.toLowerCase()
                     val filtered = mutableListOf<ListData>()
                     viewModel.filteredList.forEach {
                         if (it.name.toLowerCase().contains(searchText)) filtered.add(it)
                     }
                     adapter.addItems(filtered)
                 }
                 return false
             }
         })
     }

     private fun setupListener() {
         binding.back.setOnClickListener { this.onDestroyView() }
     }

     override fun onItemClickBottom(item: ListData) {
        // Log.d("AddBottomSheetFragment", "svs_txt = ${svs_txt.text.toString()}")
         //progressRL.visibility = View.VISIBLE
         val intent = Intent1(requireContext(), ContactsActivity::class.java)
        // intent.putExtra(AppContacts.ITEM_KEY, svs_txt.text.toString())
         startActivityForResult(intent, 1)
     }

     override fun onActivityResult(requestCode: Int, resultCode: Int, data: android.content.Intent?) {
         if (data == null)
             return
         //String.name = data.getStringExtra("name")
     }
     //закрывать боттом шит
     //передавать item.name

     override fun onLongItemClickBottom(item: ListData) {
     }
 }




