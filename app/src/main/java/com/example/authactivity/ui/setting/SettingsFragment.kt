package com.example.authactivity.ui.setting

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.authactivity.base.BaseFragment
import com.example.authactivity.databinding.FragmentSettingsBinding
import com.example.authactivity.ui.currency.CurrencyViewModel

class SettingsFragment : BaseFragment<CurrencyViewModel, FragmentSettingsBinding>(CurrencyViewModel::class) {
    override fun setupViews() {
    }

    override fun subscribeToLiveData() {
    }


    override fun attachBinding(
            list: MutableList<FragmentSettingsBinding>,
            layoutInflater: LayoutInflater,
            container: ViewGroup?,
            attachToRoot: Boolean
    ) {
        list.add(FragmentSettingsBinding.inflate(layoutInflater, container, attachToRoot))
    }

}