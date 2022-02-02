package com.example.authactivity.ui.tablayout.fragment

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import com.example.authactivity.R
import com.example.authactivity.base.BaseFragment
import com.example.authactivity.databinding.FragmentGiveBinding
import com.example.authactivity.ui.mycontacts.ContactViewModel
import kotlinx.android.synthetic.main.activity_contacts.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class GiveFragment : BaseFragment<ContactViewModel, FragmentGiveBinding>(ContactViewModel::class) {

    override fun setupViews() {
        viewModel = getViewModel (clazz = ContactViewModel::class)
    }

    override fun subscribeToLiveData() {
    }

    override fun attachBinding(
        list: MutableList<FragmentGiveBinding>,
        layoutInflater: LayoutInflater,
        container: ViewGroup?,
        attachToRoot: Boolean
    ) {
        list.add(FragmentGiveBinding.inflate(layoutInflater, container, attachToRoot))
    }

    private fun showAlertReset() {
        val alert = AlertDialog.Builder(requireContext(), R.style.Theme_AppCompat_Dialog_Alert)

        val inflater = layoutInflater.inflate(R.layout.alert_delete, null)
        alert.setView(inflater)
        val negativeButton: Button = inflater.findViewById(R.id.btn_no)
        val positiveButton: Button = inflater.findViewById(R.id.btn_yes)

        val dialog = alert.create()

        negativeButton.setOnClickListener {
            dialog.dismiss()
        }
        positiveButton.setOnClickListener {
            viewModel.deleteContact()
            txt_amount.text = "0"
            txt_category.text = toString()
            txt_name.text = toString()
            dialog.dismiss()
        }
        dialog.show()
    }
}