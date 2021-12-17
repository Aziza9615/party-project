package com.example.authactivity.ui.setting

import android.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.example.authactivity.R
import com.example.authactivity.base.BaseFragment
import com.example.authactivity.databinding.FragmentSettingsBinding
import com.example.authactivity.ui.lang.LangViewModel
import com.example.authactivity.ui.onBoard.OnBoardViewModel

class SettingsFragment : BaseFragment<LangViewModel, FragmentSettingsBinding>(LangViewModel::class) {

    override fun setupViews() {
        setupListener()
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

    private fun setupListener() {
        binding.svsTxt.setOnClickListener {
            findNavController().navigate(R.id.action_SettingsFragment_to_langSettingsFragment)
        }
        binding.ivSvs.setOnClickListener {
            findNavController().navigate(R.id.action_SettingsFragment_to_langSettingsFragment)
        }
        binding.txtShare.setOnClickListener {
            findNavController().navigate(R.id.action_SettingsFragment_to_langSettingsFragment)
        }
        binding.ivShare.setOnClickListener {
            findNavController().navigate(R.id.action_SettingsFragment_to_langSettingsFragment)
        }
        binding.txtDev.setOnClickListener {
            findNavController().navigate(R.id.action_SettingsFragment_to_langSettingsFragment)
        }
        binding.ivDev.setOnClickListener {
            findNavController().navigate(R.id.action_SettingsFragment_to_langSettingsFragment)
        }
    }
}