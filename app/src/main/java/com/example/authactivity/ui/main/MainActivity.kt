package com.example.authactivity.ui.main

import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.authactivity.base.BaseActivity
import androidx.lifecycle.Observer
import com.example.authactivity.R
import com.example.authactivity.base.BaseEvent
import com.example.authactivity.base.BaseViewModel
import com.example.authactivity.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainViewModel : BaseViewModel<BaseEvent>()
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>(MainViewModel::class) {

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var itemView = item.itemId

        when(itemView) {

            R.id.add -> Toast.makeText(applicationContext, "Add Clicked", Toast.LENGTH_LONG).show()
        }
        return true
    }

    override fun setupViews() {
        viewModel = getViewModel(clazz = MainViewModel::class)
        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)

        toolbar.setNavigationOnClickListener {
            Toast.makeText(this,"Navigation menu Clicked", Toast.LENGTH_LONG).show()
        }
    }

    override fun subscribeToLiveData() {
    }
}
