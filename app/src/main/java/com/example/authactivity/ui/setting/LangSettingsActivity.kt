package com.example.authactivity.ui.setting

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import com.example.authactivity.R
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityLangSettBinding
import com.example.authactivity.ui.main.MainViewPagerAdapter
import com.example.authactivity.ui.onBoard.OnBoardViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.util.*

class LangSettingsActivity : BaseActivity<OnBoardViewModel, ActivityLangSettBinding>(OnBoardViewModel::class) {

    private lateinit var adapter: MainViewPagerAdapter

    override fun  getViewBinding() = ActivityLangSettBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = OnBoardViewModel::class)
        setupListener()
        loadLocate()
    }

    private fun setupListener() {
        binding.arrowBtn.setOnClickListener { this.onBackPressed() }
        binding.currency.setOnClickListener {
            val intent = Intent(this, CurrencySettingsActivity::class.java)
            startActivity(intent)
        }

        binding.ivCurrency.setOnClickListener {
            val intent = Intent(this, CurrencySettingsActivity::class.java)
            startActivity(intent)
        }

        binding.lang.setOnClickListener {
            val alert = AlertDialog.Builder(this, R.style.AddDialogStyle)

            val inflater = layoutInflater.inflate(R.layout.alert_edit, null)
            alert.setView(inflater)
            val listItmes = arrayOf("English", "Кыргызча", "Русский")
            val mBuilder = AlertDialog.Builder(this)
            mBuilder.setTitle("Choose Language")
            mBuilder.setSingleChoiceItems(listItmes, -1) { dialog, which ->
                if (which == 0) {
                    setLocate("en")
                } else if (which == 1) {
                    setLocate("ky")
                } else if (which == 2) {
                    setLocate("ru")
                }
                dialog.dismiss()
            }
            val mDialog = mBuilder.create()
            mDialog.show()
        }
    }

    private fun setLocate(Lang: String) {
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
        val sharedPreferences = getDefaultSharedPreferences(this)
        val language = sharedPreferences.getString("My_Lang", "")
        if (language != null) {
            setLocate(language)
        }
    }

    override fun subscribeToLiveData() {}
}