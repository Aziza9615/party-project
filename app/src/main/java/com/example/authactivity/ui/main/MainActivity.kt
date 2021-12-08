package com.example.authactivity.ui.main

import android.app.Activity
import android.content.Intent
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.authactivity.R
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityMainBinding
import com.example.authactivity.ui.mycontacts.ContactsFragment
import com.example.authactivity.ui.onBoard.OnBoardViewModel
import com.example.authactivity.ui.setting.SettingsFragment
import com.example.authactivity.ui.statistics.StatisticsFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.util.*

class MainActivity : BaseActivity<OnBoardViewModel, ActivityMainBinding>(OnBoardViewModel::class) {

    private lateinit var adapter: MainViewPagerAdapter

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = OnBoardViewModel::class)
        setupViewPager()
        setupBottomNavigationView()
    }

    private fun setupViewPager() {
        adapter = MainViewPagerAdapter(this)
        adapter.addFragments(ContactsFragment())
        adapter.addFragments(StatisticsFragment())
        adapter.addFragments(SettingsFragment())
        view_pager.adapter = adapter
        view_pager.offscreenPageLimit = 3
        view_pager.isEnabled = false
        view_pager.isUserInputEnabled = false
    }

    private fun setupBottomNavigationView() {
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_contacts -> changeCurrentFragment(0)
                R.id.bottom_statistic -> changeCurrentFragment(1)
                R.id.bottom_settings -> changeCurrentFragment(2)
            }
            true
        }
    }

    private fun changeCurrentFragment(position: Int) {
        view_pager.setCurrentItem(position, false)
    }

    override fun subscribeToLiveData() {
    }

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, MainActivity::class.java)
            activity.startActivity(intent)
        }
    }
}