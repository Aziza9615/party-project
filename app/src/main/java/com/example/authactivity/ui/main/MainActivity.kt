package com.example.authactivity.ui.main

import android.app.Activity
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityMainBinding
import com.example.authactivity.ui.bottom.list.ListActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(MainViewModel::class) {

    private lateinit var adapter: MainViewPagerAdapter
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var mNavController: NavController

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = MainViewModel::class)
        setupListeners()
        //setSupportActionBar(binding.include.toolbar)
        setupNavDrawer()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    private fun setupNavDrawer() {
        val navDrawer = binding.navView
        val drawerLayout = binding.drawView
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view_tag) as NavHostFragment
        mNavController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(setOf(R.id.invoicesFragment2, R.id.currencyFragment2, R.id.shareFragment2, R.id.estimationFragment2, R.id.connectionFragment2), drawerLayout
        )
        setupActionBarWithNavController(mNavController, appBarConfiguration)
        navDrawer.setupWithNavController(mNavController)
    }

    private fun AppBarConfiguration(of: NavGraph, drawerLayout: DrawerLayout): AppBarConfiguration {
        TODO("Not yet implemented")
    }

    override fun onSupportNavigateUp(): Boolean {
        return mNavController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    private fun setupListeners() {
        binding.include.cardView.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }
    }

    override fun subscribeToLiveData() {}

    companion object {
        fun intent(activity: Activity) {
            val intent = Intent(activity, MainActivity::class.java)
            activity.startActivity(intent)
        }
    }
}