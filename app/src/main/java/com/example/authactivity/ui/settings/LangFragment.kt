package com.example.authactivity.ui.settings

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.recreate
import com.example.authactivity.R
import com.example.authactivity.base.BaseFragment
import com.example.authactivity.databinding.FragmentLangBinding
import com.example.authactivity.ui.settings.viewmodel.SettingsViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.util.*

class LangFragment : BaseFragment<SettingsViewModel, FragmentLangBinding>(
        SettingsViewModel::class) {

    override fun attachBinding(
            list: MutableList<FragmentLangBinding>,
            layoutInflater: LayoutInflater,
            container: ViewGroup?,
            attachToRoot: Boolean) {
        list.add(FragmentLangBinding.inflate(layoutInflater, container, attachToRoot))
            showChangeLang()
    }

    private fun showChangeLang() {
        val listItems = arrayOf("English", "Кыргызча", "Русский")
        val mBuilder = AlertDialog.Builder(context)
        mBuilder.setTitle("Choose Language")
        mBuilder.setSingleChoiceItems(listItems, -1) { dialog, which ->
            if (which == 0) {
                setLocate("en")
                getActivity()?.let { recreate(it) }

            } else if (which == 1) {
                setLocate("ky")
                getActivity()?.let { recreate(it) }

            } else if (which == 2) {
                setLocate("ru")
                getActivity()?.let { recreate(it) }
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
        //baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        val editor = this.getActivity()?.getSharedPreferences("Settings", Context.MODE_PRIVATE)
//        editor.putString("My_Lang", Lang)
//        editor.apply()
    }

    private fun loadLocate() {
        val SharedPreferences = this.getActivity()?.getSharedPreferences("Settings", Activity.MODE_PRIVATE)
        val language = SharedPreferences?.getString("My_Lang", "")
        setLocate(language)
    }

    override fun setupViews() {
        viewModel = getViewModel(clazz = SettingsViewModel::class)
        showChangeLang()
        loadLocate()
    }

    override fun subscribeToLiveData() {
    }

}