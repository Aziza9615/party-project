package com.example.authactivity.ui.main

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.authactivity.R
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityMainBinding
import com.example.authactivity.ui.bottom.HomeFragment
import com.example.authactivity.ui.bottom.ListFragment
import com.example.authactivity.ui.bottom.StatisticFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>
    (MainViewModel::class) {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var mNavController: NavController

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(mNavController) || super.onOptionsItemSelected(item)
    }

    override fun setupViews() {
        viewModel = getViewModel(clazz = MainViewModel::class)
        setSupportActionBar(binding.include.toolbar)
        setupNavDrawer()
        alert()
    }

    private fun alert() {
        binding
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

