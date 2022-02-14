package com.example.authactivity.ui.setting

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.authactivity.R
import com.example.authactivity.base.BaseFragment
import com.example.authactivity.databinding.FragmentSettingsBinding
import com.example.authactivity.local.intentToNext
import com.example.authactivity.ui.mycontacts.ContactsFragment
import com.example.authactivity.ui.onBoard.OnBoardViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SettingsFragment : BaseFragment<OnBoardViewModel, FragmentSettingsBinding>(OnBoardViewModel::class) {

    override fun setupViews() {
        viewModel = getViewModel(clazz = OnBoardViewModel::class)
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

    @SuppressLint("ResourceType")
    private fun setupListener() {
        binding.svsTxt.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.layout.fragment_lang, LangSettingsFragment())?.commit()
        }
        binding.ivSvs.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.layout.fragment_lang, LangSettingsFragment())?.commit()
        }
        binding.txtShare.setOnClickListener {
            val shareBody = "Download nextQuiz on Play Store : http://play.google.com/store/apps/details?id=com.jadebu.nextquiz&hl=en"
            val shareSub = "nextQuiz: make brain powerfull"
            val shareIntent = Intent (Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub)
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
            startActivity(shareIntent)
        }
        binding.ivShare.setOnClickListener {
            val shareBody = "Download nextQuiz on Play Store : http://play.google.com/store/apps/details?id=com.jadebu.nextquiz&hl=en"
            val shareSub = "nextQuiz: make brain powerfull"
            val shareIntent = Intent (Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub)
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
            startActivity(shareIntent)
        }
        binding.txtDev.setOnClickListener {
           // findNavController().navigate(R.id.action_SettingsFragment_to_langSettingsFragment)
        }
        binding.ivDev.setOnClickListener {
           // findNavController().navigate(R.id.action_SettingsFragment_to_langSettingsFragment)
        }
    }
}

private fun Bundle.putSerializable(s: String) {

}
