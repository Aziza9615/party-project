package com.example.authactivity.ui.tablayout

import android.content.Intent
import androidx.viewpager2.widget.ViewPager2
import com.example.authactivity.R
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityTabBinding
import com.example.authactivity.ui.currency.CurrencyActivity
import com.example.authactivity.ui.main.MainViewPagerAdapter
import com.example.authactivity.ui.onBoard.OnBoardViewModel
import com.example.authactivity.ui.tablayout.adapter.TabViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class TabActivity : BaseActivity<OnBoardViewModel, ActivityTabBinding>(OnBoardViewModel::class) {

    private lateinit var adapter: TabViewPagerAdapter

    override fun getViewBinding() = ActivityTabBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = OnBoardViewModel::class)
        setupViewPager()
        setupBottomNavigationView()
        getIntentTab()
    }

    private fun getIntentTab() {
        startActivity(Intent(this, TabActivity::class.java))
        finish()
    }

    private fun setupViewPager() {
        val tabLayout=findViewById<TabLayout>(R.id.tab_layout)
        val viewPager2=findViewById<ViewPager2>(R.id.view_pager)

        val adapter = TabViewPagerAdapter(supportFragmentManager,lifecycle)

        viewPager2.adapter=adapter

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

    private fun setupBottomNavigationView() {
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ContactsFragment -> changeCurrentFragment(0)
                R.id.StatisticFragment -> changeCurrentFragment(1)
                R.id.SettingsFragment -> changeCurrentFragment(2)
            }
            true
        }
    }

    private fun changeCurrentFragment(position: Int) {
        view_pager.setCurrentItem(position, false)
    }

    override fun subscribeToLiveData() {}
}