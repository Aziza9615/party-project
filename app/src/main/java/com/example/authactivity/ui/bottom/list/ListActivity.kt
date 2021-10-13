package com.example.authactivity.ui.bottom.list

import android.app.Activity
import android.content.Intent
import android.os.Handler
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.authactivity.R
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.base.ListEvent
import com.example.authactivity.databinding.ActivityListBinding
import com.example.authactivity.model.ListData
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ListActivity : BaseActivity<ListViewModel, ActivityListBinding>(ListViewModel::class) {

    private lateinit var addsBtn: FloatingActionButton
    private lateinit var recv: RecyclerView
    private lateinit var userList: ArrayList<ListData>
    private lateinit var userAdapter: UserAdapter

    override fun getViewBinding() = ActivityListBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = ListViewModel::class)
        userList = ArrayList()
        addsBtn = findViewById(R.id.addingBtn)
        recv = findViewById(R.id.mRecycler)
        userAdapter = UserAdapter(this, userList)
        recv.layoutManager = LinearLayoutManager(this)
        recv.adapter = userAdapter
        addsBtn.setOnClickListener { addInfo() }
        setupSearchView()
    }

    private fun addInfo() {
        val inflater = LayoutInflater.from(this)
        val v = inflater.inflate(R.layout.items_add, null)
        val userName = v.findViewById<EditText>(R.id.userName)
        val userSub = v.findViewById<EditText>(R.id.category)
        val userNo = v.findViewById<EditText>(R.id.userAmount)

        val addDialog = AlertDialog.Builder(this)

        addDialog.setView(v)
        addDialog.setPositiveButton("Ok") { dialog, _ ->
            val names = userName.text.toString()
            val category = userSub.text.toString()
            val number = userNo.text.toString()
            userList.add(ListData("Name: $names", "Category. : $category", "Sum. : $number"))
            userAdapter.notifyDataSetChanged()
            Toast.makeText(this, "Adding User Information Success", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
            Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }


    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object  : OnQueryTextListener, androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                binding.searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                Handler().postDelayed(Runnable {
                    if (newText == "") {
                        viewModel.fetchList()
                    } else {

                        val searchText = newText.toLowerCase()
                        val filtered = mutableListOf<ListData>()
                        viewModel.list.forEach { if (it.userName.toLowerCase().contains(searchText)) filtered.add(it) }
                        userAdapter.addItems(filtered)
                    }
                }, 800)
                return false
            }
        })
    }

    companion object {
        fun intent(activity: Activity) {
            val intent = Intent(activity, ListActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun subscribeToLiveData() {
    }
}
