package com.example.authactivity.ui.activity

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.widget.Button
import com.example.authactivity.R
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityEmblemBinding
import com.example.authactivity.ui.activity.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.util.*

class EmblemActivity : BaseActivity<UserViewModel, ActivityEmblemBinding>(UserViewModel::class) {

    lateinit var mBtn : Button

    override fun getViewBinding() = ActivityEmblemBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = UserViewModel::class)
        setupListener()
        loadLocate()

        mBtn = findViewById(R.id.language)
        mBtn.setOnClickListener {
            showChangeLang()
        }
    }

    private fun setupListener() {
        binding.btnNext.setOnClickListener {
            val intent = Intent(this, AmountActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showChangeLang() {
        val listItems = arrayOf("English", "Кыргызча", "Русский")

        val mBuilder = AlertDialog.Builder(this@EmblemActivity)
        mBuilder.setTitle("Choose Language")
        mBuilder.setSingleChoiceItems(listItems, -1) { dialog, which ->
            if (which == 0) {
                setLocate("en")
                recreate()

            } else if (which == 1) {
                setLocate("ky")
                recreate()

            } else if (which == 2) {
                setLocate("ru")
                recreate()
        }

            dialog.dismiss()
        }

        val mDialog = mBuilder.create()
        mDialog.show()
    }

    private fun setLocate(Lang: String?) {
        val locale = Locale(Lang)
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        val editor = getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
        editor.putString("My_Lang", Lang)
        editor.apply()
    }

    private fun loadLocate() {
        val SharedPreferences =  getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val language = SharedPreferences.getString("My_Lang", "")
        setLocate(language)
    }

    override fun subscribeToLiveData() {
    }

    companion object {
        fun intent(activity: Activity) {
            val intent = Intent(activity, EmblemActivity::class.java)
            activity.startActivity(intent)
        }
    }
}