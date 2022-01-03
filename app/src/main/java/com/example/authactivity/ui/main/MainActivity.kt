package com.example.authactivity.ui.main


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.authactivity.R
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityMainBinding
import com.example.authactivity.ui.mycontacts.ContactsFragment
import com.example.authactivity.ui.onBoard.OnBoardViewModel
import com.example.authactivity.ui.setting.SettingsFragment
import com.example.authactivity.ui.statistics.StatisticsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val navController = this.findNavController(R.id.fragment_nav)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
       // NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.fragment_nav)
        return navController.navigateUp()
    }
}