package com.example.authactivity.ui.main

import android.app.Activity
import android.app.AlertDialog
import android.app.admin.SystemUpdatePolicy
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.util.AttributeSet
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import androidx.preference.PreferenceManager
import com.example.authactivity.R
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityMainBinding
import com.example.authactivity.model.ListData
import com.example.authactivity.ui.bottom.list.ListActivity
import com.example.authactivity.ui.settings.SettingsActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_sett.*
import kotlinx.android.synthetic.main.alert_delete.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.w3c.dom.Text
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
        mySettings()
    }

    val prefs = PreferenceManager
        .getDefaultSharedPreferences(this)

    val name = prefs.getString(
        "signature", ""
    )

    val list = prefs.getString("list", "")
    val switch = prefs.getBoolean("switch", false)
    val text = prefs.getBoolean("text", false)

    private fun mySettings() {
        tvText.text = text.toString()
        binding.apply {
            val alert = AlertDialog.Builder(this@MainActivity)
            val inflater = layoutInflater.inflate(R.layout.alert_delete, null)
            alert.setView(inflater)
            val negativeButton: Button = inflater.findViewById(R.id.negative_button)
            val positiveButton: Button = inflater.findViewById(R.id.positive_button)
            val dialog = alert.create()
            negativeButton.setOnClickListener {
                dialog.dismiss()
            }
            positiveButton.setOnClickListener {
                viewModel.deleteAlData()
                dialog.dismiss()
            }
            dialog.show()
        }

        if (switch) {
            tvSwitch.text = "switch is on"
        } else {
            tvSwitch.text = "switch is off"
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
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