 package com.example.authactivity.ui.mycontacts.bottomSheet

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
import com.example.authactivity.databinding.LayoutAddBottomSheetBinding
import com.example.authactivity.local.PrefsHelper
import com.example.authactivity.local.isEmptyInputData
import com.example.authactivity.local.showAlertDone1
import com.example.authactivity.model.ListData
import com.example.authactivity.ui.mycontacts.*
import com.example.authactivity.ui.onBoard.OnBoardActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

 class AddBottomSheetFragment : BaseAddBottomSheetFragment(), ClickListenerBottom {

     lateinit var binding: LayoutAddBottomSheetBinding
     private lateinit var viewModel: ListViewModel
     private lateinit var adapter: AdapterBottomSheet

     override fun onCreateView(
         inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?
     ): View {
         binding = LayoutAddBottomSheetBinding.inflate(
             inflater, container, false
         )
         return binding.root
     }

     override fun setupViews() {
         viewModel = getViewModel(clazz = ListViewModel::class)
         PrefsHelper.instance = PrefsHelper(requireContext())
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

     private fun subscribe() {
         viewModel.data.observe(viewLifecycleOwner, androidx.lifecycle.Observer { adapter.addItems(it) })
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
                 showAlertDone1(requireContext(), layoutInflater, R.layout.alert_done1)
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
         val list = ListData(id = 0, name = nameEditText.text.toString())
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
                         if (it.name?.toLowerCase()!!.contains(searchText)) filtered.add(it)
                     }
                     adapter.addItems(filtered)
                 }
                 return false
             }
         })
     }

     private fun setupListener() {
         binding.back.setOnClickListener {
             startActivity(Intent(requireContext(), ContactActivity::class.java))
         }
     }

     override fun getTheme(): Int {
         return R.style.RoundedCornerBottomSheetDialog
     }

     override fun onItemClickBottom(item: ListData) {
         val intent = android.content.Intent(requireContext(), ContactActivity::class.java)
         PrefsHelper.instance.saveName(item.name!!)
         PrefsHelper.instance.saveNameId(item.id)
         startActivity(intent)
     }

     override fun onLongItemClickBottom(item: ListData) {}
 }






