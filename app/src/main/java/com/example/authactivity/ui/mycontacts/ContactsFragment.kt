package com.example.authactivity.ui.mycontacts

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import com.example.authactivity.base.BaseFragment
import com.example.authactivity.databinding.FragmentContactsBinding
import com.example.authactivity.model.ListData

class ContactsFragment : BaseFragment<ContactsViewModel, FragmentContactsBinding>(ContactsViewModel::class) {

    var item = mutableListOf<ListData>()

    override fun attachBinding(
        list: MutableList<FragmentContactsBinding>,
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ) {
        list.add(FragmentContactsBinding.inflate(layoutInflater, container, attachToRoot))
    }

    override fun setupViews() {
        setupListeners()
    }

    private fun setupListeners() {
        binding.newBtn.setOnClickListener {
            val intent = Intent(requireContext(), ContactsActivity::class.java)
            intent.putExtra(PRESENT_ITEM)
            startActivity(intent)

        }
    }

    companion object {
        const val PRESENT_ITEM = "PRESENT_ITEM"
    }

    override fun subscribeToLiveData() {
    }
}

private fun Intent.putExtra(presentItem: String) {

}

