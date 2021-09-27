package com.example.authactivity.ui.main

import android.app.Activity
import android.content.Intent
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.authactivity.R
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityMainBinding
import com.example.authactivity.ui.bottom.HomeFragment
import com.example.authactivity.ui.bottom.ListFragment
import com.example.authactivity.ui.bottom.StatisticFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>
    (MainViewModel::class) {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var mNavController: NavController

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun setupViews() {
        setSupportActionBar(binding.include.toolbar)
        setupNavDrawer()
    }

    private fun setupNavDrawer() {
        val navDrawer = binding.navView
        val drawerLayout = binding.drawView
        val navBottomView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentHost) as NavHostFragment
         mNavController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.invoicesFragment, R.id.currencyFragment, R.id.shareFragment, R.id.estimationFragment, R.id.connectionFragment
            ), drawerLayout
        )
        setupActionBarWithNavController(mNavController, appBarConfiguration)
        navDrawer.setupWithNavController(mNavController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return mNavController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun subscribeToLiveData() {
    }

    companion object {
        fun intent(activity: Activity) {
            val intent = Intent(activity,MainActivity::class.java)
            activity.startActivity(intent)
        }
    }
}

