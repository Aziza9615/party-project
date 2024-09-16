package com.example.authactivity.ui.mycontacts

import android.app.AlertDialog
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.*
import com.example.authactivity.R
import com.example.authactivity.base.BaseActivity
import com.example.authactivity.databinding.ActivityContactBinding
import com.example.authactivity.local.PrefsHelper
import com.example.authactivity.model.ContactData
import com.example.authactivity.ui.main.MainActivity
import com.example.authactivity.ui.mycontacts.bottomSheet.AddBottomSheetFragment
import com.example.authactivity.ui.mycontacts.category.CategoryBottomSheetFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_currency.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ContactActivity : BaseActivity<ContactViewModel, ActivityContactBinding>(ContactViewModel::class), ClickListener {

    override fun  getViewBinding() = ActivityContactBinding.inflate(layoutInflater)

    private lateinit var contact: ContactData

    override fun setupViews() {
        viewModel = getViewModel(clazz = ContactViewModel::class)
        PrefsHelper.instance = PrefsHelper(this)
        getIntentData()
        setupListeners()
        showEditTextDialogTwo()
        setupRadio()
    }

    private fun setupRadio() {
        binding.radio.setOnCheckedChangeListener { group, checkedId ->
            var rb = findViewById<RadioButton>(checkedId)
            if(checkedId == R.id.button_accept) {
                PrefsHelper.instance.saveAccept((rb.id.toString()))
            }
            if (checkedId == R.id.button_give) {
                PrefsHelper.instance.saveGive((rb.id.toString()))
            }
        }
    }

    private fun getIntentData() {
        binding.txtCategory.text = PrefsHelper.instance.getCategory()
        binding.txtName.text = PrefsHelper.instance.getName()
        binding.arrowBtn.setOnClickListener {
            startActivity(Intent(this@ContactActivity, MainActivity::class.java))
        }
    }

    private val loginTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val name = binding.txtName.text.toString().trim()
            val category = binding.txtCategory.text.toString().trim()
            val amount = binding.txtAmount.text.toString().trim()
            binding.nextBtn.isEnabled = name.isNotEmpty() && category.isNotEmpty() && amount.isNotEmpty()
        }
        override fun afterTextChanged(s: Editable?) {}
    }

    private fun insertSoldProduct(amount: Int, name: String, category: String) {
        contact = ContactData(
            PrefsHelper.instance.getNameId()!!,
            name,
            category,
            amount
        )
        viewModel.insertContact(contact)
    }

    private fun setupListeners() {
        binding.txtCategory.setOnClickListener {
            val bottomSheetDialogFragment: BottomSheetDialogFragment = CategoryBottomSheetFragment()
            bottomSheetDialogFragment.isCancelable = true
            bottomSheetDialogFragment.show(supportFragmentManager, bottomSheetDialogFragment.tag)
        }
        binding.txtName.setOnClickListener {
            val bottomSheetDialogFragment: BottomSheetDialogFragment = AddBottomSheetFragment()
            bottomSheetDialogFragment.isCancelable = true
            bottomSheetDialogFragment.show(supportFragmentManager, bottomSheetDialogFragment.tag)
        }
        binding.txtName.addTextChangedListener(loginTextWatcher)
        binding.txtCategory.addTextChangedListener(loginTextWatcher)
        binding.txtAmount.addTextChangedListener(loginTextWatcher)
        binding.nextBtn.setOnClickListener {
            saveContacts()
            startActivity(Intent(this@ContactActivity, MainActivity::class.java))
        }
    }

    private fun saveContacts() {
        val id = PrefsHelper.instance.getNameId()
        val name = PrefsHelper.instance.getName()
        val category = PrefsHelper.instance.getCategory()
        val amount = PrefsHelper.instance.getAmount().toString().toInt()
        val contact = ContactData(id!!, name, category, amount)
        insertSoldProduct(amount, name!!, category!!)
        PrefsHelper.instance.saveCategory("")
        PrefsHelper.instance.saveName("")
        PrefsHelper.instance.saveAmount("")
        PrefsHelper.instance.saveNameId(0)
    }

    private fun showEditTextDialogTwo() {
        binding.txtAmount.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.item_currency, null)
            val editText = dialogLayout.findViewById<EditText>(R.id.dialog_text)
            with(builder) {
                setTitle("Введите сумму ()")
                setPositiveButton("Сохранить") { dialog, which ->
                    PrefsHelper.run { instance.saveAmount(editText.text.toString()) }
                    binding.txtAmount.text = editText.text.toString()
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
    override fun onItemClick(item: ContactData) {}
    override fun onLongItemClick(item: ContactData) {}
}