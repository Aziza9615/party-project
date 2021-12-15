package com.example.authactivity.ui.mycontacts

import android.app.AlertDialog
import android.util.Log
import android.widget.EditText
import com.example.authactivity.R
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityContactsBinding
import com.example.authactivity.local.PrefsHelper
import com.example.authactivity.ui.category.CategoryBottomSheetFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ContactsActivity : BaseActivity<ContactsViewModel, ActivityContactsBinding>(ContactsViewModel::class) {

    override fun getViewBinding() = ActivityContactsBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = ContactsViewModel::class)
        PrefsHelper.instance = PrefsHelper(this)
        getIntentData()
        setupListeners()
        showEditTextDialogTwo()
        binding.amountTextView.text = PrefsHelper.instance.getAmount().toString()
    }

    private fun getIntentData() {
        val present = intent.getSerializableExtra(ContactsFragment.PRESENT_ITEM)
        binding.arrowBtn.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setupListeners() {
        binding.btnName.setOnClickListener {
            val bottomSheetDialogFragment: BottomSheetDialogFragment =
                    AddBottomSheetFragment(this)
            bottomSheetDialogFragment.isCancelable = true
            bottomSheetDialogFragment.show(
                    supportFragmentManager,
                    bottomSheetDialogFragment.tag
            )
        }
        binding.btnCategory.setOnClickListener {
            val bottomSheetDialogFragment: BottomSheetDialogFragment =
                    CategoryBottomSheetFragment(this)
            bottomSheetDialogFragment.isCancelable = true
            bottomSheetDialogFragment.show(
                    supportFragmentManager,
                    bottomSheetDialogFragment.tag
            )
        }
    }

    private fun showEditTextDialogTwo() {
        binding.amountTextView.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.item_currency, null)
            val editText = dialogLayout.findViewById<EditText>(R.id.dialog_text)
            with(builder) {
                setTitle("Другие источники дохода (${PrefsHelper.instance.getAmount()})")
                setPositiveButton("Сохранить") { dialog, which ->
                    PrefsHelper.run { instance.saveAmount(editText.text.toString().toInt()) }
                    binding.amountTextView.text = editText.text.toString()
                }
                setNegativeButton("Отмена") { dialog, which ->
                    Log.d("Main", "Negative Button Click")
                }
                setView(dialogLayout)
                show()
            }
        }
    }

    override fun subscribeToLiveData() {
    }
}