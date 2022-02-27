package com.example.authactivity.ui.tablayout.bottomsheetEdit

import android.app.AlertDialog
import android.content.Intent
import android.util.Log
import android.widget.EditText
import com.example.authactivity.R
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityEditBinding
import com.example.authactivity.local.PrefsHelper
import com.example.authactivity.model.ContactData
import com.example.authactivity.model.EditData
import com.example.authactivity.ui.mycontacts.ContactViewModel
import com.example.authactivity.ui.mycontacts.ContactsFragment
import com.example.authactivity.ui.tablayout.TabActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.getViewModel

class EditActivity : BaseActivity<EditViewModel, ActivityEditBinding>(EditViewModel::class) {

    override fun getViewBinding() = ActivityEditBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = EditViewModel::class)
        PrefsHelper.instance = PrefsHelper(this)
        setupListeners()
        showEditTextDialogTwo()
        initViews()
        binding.txtAmount1.text = PrefsHelper.instance.getAmount().toString()
        binding.txtName1.text = PrefsHelper.instance.getName().toString()
        binding.txtCategory1.text = PrefsHelper.instance.getCategory().toString()
    }

    private fun initViews() {
        val contact = intent.getSerializableExtra(ContactsFragment.name_detail) as? ContactData
        binding.txtCategory1.text = contact?.name.toString()
        binding.txtAmount1.text = contact?.amount.toString()
    }

    private fun setupListeners() {
//        binding.txtCategory1.setOnClickListener {
//            val bottomSheetDialogFragment: BottomSheetDialogFragment = EditCategoryFragment()
//            bottomSheetDialogFragment.isCancelable = true
//            bottomSheetDialogFragment.show(supportFragmentManager, bottomSheetDialogFragment.tag)
//        }
//        binding.txtName1.setOnClickListener {
//            val bottomSheetDialogFragment: BottomSheetDialogFragment = EditNameFragment()
//            bottomSheetDialogFragment.isCancelable = true
//            bottomSheetDialogFragment.show(supportFragmentManager, bottomSheetDialogFragment.tag)
//        }
        binding.arrowBtn.setOnClickListener {
            startActivity(Intent(this@EditActivity, TabActivity::class.java))
        }
        binding.nextBtn.setOnClickListener {
            saveContacts()
            startActivity(Intent(this@EditActivity, TabActivity::class.java))
        }
    }

    private fun saveContacts() {
        val id = PrefsHelper.instance.getNameId()
        val category = PrefsHelper.instance.getCategory()
        val amount = PrefsHelper.instance.getAmount().toString().toInt()
        val edit = EditData(id!!, category!!, amount)
        viewModel.updateEdit(edit)
    }

    private fun showEditTextDialogTwo() {
        binding.txtAmount1.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.item_currency, null)
            val editText = dialogLayout.findViewById<EditText>(R.id.dialog_text)
            with(builder) {
                setTitle("Введите сумму (${PrefsHelper.instance.getAmount()})")
                setPositiveButton("Сохранить") { dialog, which ->
                    PrefsHelper.run { instance.saveAmount(editText.text.toString()) }
                    binding.txtAmount1.text = editText.text.toString()
                }
                setNegativeButton("Отмена") { dialog, which ->
                    Log.d("Main", "Negative Button Click")
                }
                setView(dialogLayout)
                show()
            }
        }
    }

    override fun subscribeToLiveData() {}
}