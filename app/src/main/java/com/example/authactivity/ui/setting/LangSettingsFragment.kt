package com.example.authactivity.ui.setting

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.authactivity.base.BaseFragment
import com.example.authactivity.databinding.FragmentLangSettingsBinding
import com.example.authactivity.databinding.FragmentSettingsBinding
import com.example.authactivity.ui.onBoard.OnBoardViewModel

class LangSettingsFragment : BaseFragment<OnBoardViewModel, FragmentLangSettingsBinding>(OnBoardViewModel::class) {
    override fun setupViews() {
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