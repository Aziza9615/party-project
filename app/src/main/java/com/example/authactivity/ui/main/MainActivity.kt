package com.example.authactivity.ui.main

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import androidx.preference.PreferenceManager
import com.example.authactivity.R
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityMainBinding
import com.example.authactivity.ui.bottom.list.ListActivity
import com.example.authactivity.ui.emblem.EmblemFragment
import com.example.authactivity.ui.settings.bottomSheet.CustomDialogFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_sett.*
import kotlinx.android.synthetic.main.alert_delete.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.util.*

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(MainViewModel::class) {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var mNavController: NavController

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = MainViewModel::class)
        setSupportActionBar(binding.item1.toolbar)
        setupListeners()
        setupNavDrawer()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_toolbar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.langFragment-> {
                var dialog = CustomDialogFragment()
                dialog.show(supportFragmentManager,"customDialog")
                true
            }
            R.id.delete-> {
                true
            }
            R.id.theme-> {
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupNavDrawer() {
        val navDrawer = binding.navView
        val drawerLayout = binding.drawView
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentHost) as NavHostFragment
        mNavController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.connectionFragment2, R.id.currencyFragment2, R.id.estimationFragment2, R.id.shareFragment2), drawerLayout)
        setupActionBarWithNavController(mNavController, appBarConfiguration)
        navDrawer.setupWithNavController(mNavController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return mNavController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

        private fun setupListeners() {
        binding.item2.cardView.setOnClickListener {
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