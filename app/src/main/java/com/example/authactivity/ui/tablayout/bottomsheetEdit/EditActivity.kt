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
import com.example.authactivity.ui.main.MainActivity
import com.example.authactivity.ui.mycontacts.ContactViewModel
import com.example.authactivity.ui.mycontacts.ContactsFragment
import com.example.authactivity.ui.tablayout.TabActivity
import org.koin.androidx.viewmodel.ext.android.getViewModel

class EditActivity : BaseActivity<ContactViewModel, ActivityEditBinding>(ContactViewModel::class) {

    private lateinit var contact: ContactData

    override fun getViewBinding() = ActivityEditBinding.inflate(layoutInflater)

    override fun setupViews() {
        viewModel = getViewModel(clazz = ContactViewModel::class)
        PrefsHelper.instance = PrefsHelper(this)
        setupListeners()
        showEditTextDialogTwo()
        initViews()
        binding.name.text = PrefsHelper.instance.getName().toString()
        binding.category.text = PrefsHelper.instance.getCategory().toString()
    }

    private fun initViews() {
        contact = ((intent.getSerializableExtra(ContactsFragment.name_detail) as? ContactData)!!)
        binding.txtName.text = contact.name.toString()
        binding.txtCategory1.text = (contact.category.toString())
        binding.amountTxt.setText(contact.amount.toString())
    }

    private fun setupListeners() {
        binding.arrowBtn.setOnClickListener {
            startActivity(Intent(this@EditActivity, MainActivity::class.java))
        }
        binding.nextBtn.setOnClickListener {
            saveContacts()
            startActivity(Intent(this@EditActivity, TabActivity::class.java))
        }
    }

    private fun saveContacts() {
        val name = binding.txtName.text.toString()
        val category = binding.category.text.toString()
        val amount = binding.amountTxt.text.toString().toInt()

        contact = ContactData(contact.id ,name, category, amount)

        viewModel.updateContact(contact)
    }

    private fun showEditTextDialogTwo() {
        binding.amountTxt.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.item_currency, null)
            val editText = dialogLayout.findViewById<EditText>(R.id.dialog_text)
            with(builder) {
                setTitle("Введите сумму (${PrefsHelper.instance.getAmount()})")
                setPositiveButton("Сохранить") { dialog, which ->
                    PrefsHelper.run { instance.saveAmount(editText.text.toString()) }
                    binding.amountTxt.text = editText.text.toString()
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