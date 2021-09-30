package com.example.authactivity.ui.settings

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.authactivity.base.BaseFragment
import com.example.authactivity.databinding.FragmentDeleteBinding
import com.example.authactivity.ui.settings.viewmodel.SettingsViewModel

class DeleteFragment : BaseFragment<SettingsViewModel, FragmentDeleteBinding>(
        SettingsViewModel::class) {

    override fun attachBinding(
            list: MutableList<FragmentDeleteBinding>,
            layoutInflater: LayoutInflater,
            container: ViewGroup?,
            attachToRoot: Boolean) {
        list.add(FragmentDeleteBinding.inflate(layoutInflater, container, attachToRoot))
    }

    override fun setupViews() {
    }

    override fun subscribeToLiveData() {

    }
}