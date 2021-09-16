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
import com.example.authactivity.base.BaseEvent
import com.example.authactivity.base.BaseViewModel
import com.example.authactivity.databinding.ActivityMainBinding

class MainViewModel : BaseViewModel<BaseEvent>()

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
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentHost) as NavHostFragment
         mNavController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.listFragment, R.id.statisticsFragment
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

