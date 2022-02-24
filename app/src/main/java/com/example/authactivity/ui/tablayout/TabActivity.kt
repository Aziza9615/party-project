package com.example.authactivity.ui.tablayout

import android.app.AlertDialog
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import androidx.viewpager2.widget.ViewPager2
import com.example.authactivity.R
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityTabBinding
import com.example.authactivity.local.PrefsHelper
import com.example.authactivity.model.ContactData
import com.example.authactivity.ui.main.MainActivity
import com.example.authactivity.ui.mycontacts.ContactActivity
import com.example.authactivity.ui.mycontacts.ContactViewModel
import com.example.authactivity.ui.mycontacts.ContactsFragment.Companion.name_detail
import com.example.authactivity.ui.tablayout.adapter.TabViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_contacts.*
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class TabActivity : BaseActivity<ContactViewModel, ActivityTabBinding>(ContactViewModel::class) {

    private lateinit var adapter: TabViewPagerAdapter

    override fun getViewBinding() = ActivityTabBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = ContactViewModel::class)
        setupViewPager2()
        setupListener()
        initViews()
    }

    private fun initViews() {
        val contact = intent.getSerializableExtra(name_detail) as ContactData
        binding.name.text = contact.name
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
            viewModel.deleteContact()
            viewModel.getContact()
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun setupViewPager2() {
        val tabLayout=findViewById<TabLayout>(R.id.tab_layout)
        val viewPager2=findViewById<ViewPager2>(R.id.view_pager)

        val adapter = TabViewPagerAdapter(supportFragmentManager,lifecycle)

        viewPager2. adapter = adapter

        TabLayoutMediator(tabLayout,viewPager2){tab,position->
            when(position){
                0->{
                    tab.text="Вы"
                }
                1->{
                    tab.text="Вам"
                }
            }
        }.attach()
    }

    companion object {
        val edit_detail = "EDIT_DETAIL"
    }

    override fun subscribeToLiveData() {}
}