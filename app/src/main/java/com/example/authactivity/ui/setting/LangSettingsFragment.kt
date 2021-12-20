package com.example.authactivity.ui.setting

import android.app.AlertDialog
import android.content.Intent
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import com.example.authactivity.base.BaseFragment
import com.example.authactivity.databinding.FragmentLangSettingsBinding
import com.example.authactivity.ui.lang.LangViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.util.*

class LangSettingsFragment : BaseFragment<LangViewModel, FragmentLangSettingsBinding>(LangViewModel::class) {

    override fun setupViews() {
        viewModel = getViewModel(clazz = LangViewModel::class)
        setupListener()
        loadLocate()
    }

    private fun setupListener() {
        binding.arrowBtn.setOnClickListener { this.onDestroyView() }
        binding.currency.setOnClickListener(View.OnClickListener() {
            val intent = Intent(requireContext(), CurrencySettingsActivity::class.java)
            startActivity(intent)
        })
        binding.ivCurrency.setOnClickListener(View.OnClickListener() {
            val intent = Intent(requireContext(), CurrencySettingsActivity::class.java)
            startActivity(intent)
        })
        binding.lang.setOnClickListener {
            val listItmes = arrayOf("English", "Кыргызча", "Русский")
            val mBuilder = AlertDialog.Builder(requireContext())
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
            context?.resources?.updateConfiguration(config, requireActivity().resources.displayMetrics)
            val editor = getDefaultSharedPreferences(requireContext()).edit()
            editor.putString("My_Lang", Lang)
            editor.apply()
        }

        private fun loadLocate() {
            val sharedPreferences = getDefaultSharedPreferences(requireContext())
            val language = sharedPreferences.getString("My_Lang", "")
            if (language != null) {
                setLocate(language)
            }
        }

    override fun subscribeToLiveData() {
    }

    override fun attachBinding(
        list: MutableList<FragmentLangSettingsBinding>,
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ) {
        list.add(FragmentLangSettingsBinding.inflate(layoutInflater, container, attachToRoot))
    }
}