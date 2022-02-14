package com.example.authactivity.ui.main

import com.example.authactivity.R
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityMainBinding
import com.example.authactivity.ui.mycontacts.ContactsFragment
import com.example.authactivity.ui.onBoard.OnBoardViewModel
import com.example.authactivity.ui.setting.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : BaseActivity<OnBoardViewModel, ActivityMainBinding>(OnBoardViewModel::class){

    private lateinit var adapter: MainViewPagerAdapter

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel (clazz = OnBoardViewModel::class)
        setupViewPager()
        setupBottomNavigationView()
    }

    private fun setupViewPager() {
        adapter = MainViewPagerAdapter(this)
        adapter.addFragments(ContactsFragment())
        adapter.addFragments(SettingsFragment())
        view_pager.adapter = adapter
        view_pager.offscreenPageLimit = 2
        view_pager.isEnabled = false
        view_pager.isUserInputEnabled = false
    }

    private fun setupBottomNavigationView() {
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ContactsFragment -> changeCurrentFragment(0)
                R.id.SettingsFragment -> changeCurrentFragment(1)
            }
            true
        }
    }

    private fun changeCurrentFragment(position: Int) {
        view_pager.setCurrentItem(position, false)
    }

    override fun subscribeToLiveData() {
    }

}