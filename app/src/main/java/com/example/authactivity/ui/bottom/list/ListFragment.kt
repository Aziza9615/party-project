package com.example.authactivity.ui.bottom.list

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.authactivity.R
import com.example.authactivity.base.BaseFragment
import com.example.authactivity.databinding.FragmentListBinding
import com.example.authactivity.model.BottomModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ListFragment : BaseFragment<ListViewModel,FragmentListBinding>(
    ListViewModel::class) {

    private lateinit var addBtn: FloatingActionButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var userList: ArrayList<BottomModel>
    private lateinit var userAdapter: ListAdapter

    override fun attachBinding(list: MutableList<FragmentListBinding>, layoutInflater: LayoutInflater, container: ViewGroup?, attachToRoot: Boolean) {
        list.add(FragmentListBinding.inflate(layoutInflater, container, attachToRoot))
        userList = ArrayList()
        addBtn = requireView().findViewById(R.id.addingBtn)
        recyclerView = requireView().findViewById<RecyclerView>(R.id.mRecycler)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = userAdapter
        userAdapter = ListAdapter(this,userList)
        addBtn.setOnClickListener { addInfo() }
    }

    private fun addInfo () {
        val inflater = LayoutInflater.from(requireContext())
        val view = inflater.inflate(R.layout.items_add, null)
        val userName = requireView().findViewById<EditText>(R.id.userName)
        val userCategory = requireView().findViewById<EditText>(R.id.category)
        val userAmount = requireView().findViewById<EditText>(R.id.userAmount)
        val addDialog = AlertDialog.Builder(requireContext())
        addDialog.setView(view)
        addDialog.setPositiveButton("Ok") {
            dialog, _ ->
            val names = userName.text.toString()
            val category = userCategory.text.toString()
            val userAmount = userAmount.text.toString()
            userList.add(BottomModel("Name: $names", "category: $category", "amount: $userAmount"))
            userAdapter.notifyDataSetChanged()
            Toast.makeText(getActivity(),"Ok", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel") {
            dialog, _ ->
            dialog.dismiss()
            Toast.makeText(getActivity(),"Cancel", Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }

    override fun setupViews() {
        viewModel = getViewModel(clazz = ListViewModel::class)
    }

    override fun subscribeToLiveData() {
    }
}
