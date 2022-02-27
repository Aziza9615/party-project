package com.example.authactivity.ui.tablayout

import android.app.AlertDialog
import android.content.Intent
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.authactivity.R
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityTabBinding
import com.example.authactivity.model.ContactData
import com.example.authactivity.model.EditData
import com.example.authactivity.ui.main.MainActivity
import com.example.authactivity.ui.mycontacts.ContactViewModel
import com.example.authactivity.ui.mycontacts.ContactsFragment.Companion.name_detail
import com.example.authactivity.ui.tablayout.adapter.EditAdapter
import com.example.authactivity.ui.tablayout.adapter.TabViewPagerAdapter
import com.example.authactivity.ui.tablayout.bottomsheetEdit.EditActivity
import com.example.authactivity.ui.tablayout.bottomsheetEdit.EditViewModel
import kotlinx.android.synthetic.main.activity_contact.*
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_tab.*
import kotlinx.android.synthetic.main.item_give.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class TabActivity : BaseActivity<EditViewModel, ActivityTabBinding>(EditViewModel::class), EditAdapter.ClickListenerAccept {

    private lateinit var adapter: EditAdapter

    override fun getViewBinding() = ActivityTabBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = EditViewModel::class)
        //setupViewPager2()
        setupListener()
        setupRecyclerView()
        //setupTabLayout()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getEdit()
    }

    private fun setupRecyclerView() {
        val contact = intent.getSerializableExtra(name_detail) as? ContactData
        binding.name.text = contact?.name
        adapter = EditAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun setupListener() {
        binding.arrowBtn.setOnClickListener {
            startActivity(Intent(this@TabActivity, MainActivity::class.java))
        }
        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this@TabActivity, EditActivity::class.java))
        }
        binding.btnDelete.setOnClickListener {
            showDelete()
        }
    }

    private fun showDelete() {
        val alert = AlertDialog.Builder(this, R.style.AlertDialogStyle)
        val inflater = layoutInflater.inflate(R.layout.alert_delete, null)
        alert.setView(inflater)
        val negativeButton: Button = inflater.findViewById(R.id.btn_no)
        val positiveButton: Button = inflater.findViewById(R.id.btn_yes)

        val dialog = alert.create()

        negativeButton.setOnClickListener {
            dialog.dismiss()
        }
        positiveButton.setOnClickListener {
            viewModel.deleteEdit()
            viewModel.getEdit()
            dialog.dismiss()
        }
        dialog.show()
    }

//    private fun setupViewPager2() {
//        adapter = TabViewPagerAdapter(supportFragmentManager)
//        adapter.addFragment(AcceptFragment(), "Вы")
//        adapter.addFragment(GiveFragment(), "Вам")
//        binding.viewPagerTab.adapter = adapter
//    }
//
//    private fun setupTabLayout() {
//        binding.tabLayout.setupWithViewPager(view_pager_tab)
//        binding.tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                tab?.let { binding.viewPagerTab.currentItem = it.position }
//            }
//            override fun onTabUnselected(tab: TabLayout.Tab?) {}
//            override fun onTabReselected(tab: TabLayout.Tab?) {}
//        })
//    }

    companion object {
        val edit_detail = "EDIT_DETAIL"
    }

    private fun subscribe() {
        viewModel.data.observe(this,
            androidx.lifecycle.Observer { adapter.addItems(it) })
    }

    override fun subscribeToLiveData() {
        viewModel.data.observe(this, androidx.lifecycle.Observer {
            adapter.addItems(it)
        })
    }

    override fun onListClick(item: EditData) {}

    override fun onLongItemClickList(item: EditData) {}
}